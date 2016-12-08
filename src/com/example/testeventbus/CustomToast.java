package com.example.testeventbus;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class CustomToast extends BaseThread{
	
		private static final int SHOW_TIME = 2000; // ��ʾʱ��
		private static final int QUEUE_SIZE = 120; // ���д�С
		private static final int QUEUE_SIZE_LIMIT = 100; // ���ƶ��д�С
		private static final int FLAG_SHOW = 1000; // ��ʾ
		private static final int FLAG_HIDE = 1001; // ����
		private static final int FLAG_CLEAR = 1002; // ������Ϣ����
		private static final String QUITMSG = "@bian_#feng_$market_%toast_&quit_*flag"; // �˳��ı��

		private static BlockingQueue<String> mMsgQueue = new ArrayBlockingQueue<String>(QUEUE_SIZE); // ��Ϣ�������

		private static CustomToast mToast;

		private WindowManager mWindowManager;
		private WindowManager.LayoutParams mParams;
		private View toastView;
		private TextView tvAlert;

		@SuppressLint("InflateParams")
		private CustomToast(Context context) {
			mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

			mParams = new WindowManager.LayoutParams();
			mParams.type = WindowManager.LayoutParams.TYPE_TOAST; //TYPE_SYSTEM_OVERLAY
			mParams.windowAnimations = android.R.style.Animation_Toast;
			mParams.format = PixelFormat.TRANSLUCENT;
			mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
			mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
			mParams.gravity = Gravity.CENTER_HORIZONTAL|Gravity.TOP;
			mParams.alpha = 1f;// ͸���ȣ�0ȫ͸ ��1��͸
			mParams.verticalMargin = 0.75f;
			mParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
	                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
	                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

			toastView = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
			tvAlert = (TextView) toastView.findViewById(R.id.tvAlert);

			start();
		}

		/**
		 * ��ʼ����Ϣ��ʾ
		 * 
		 * @param context
		 */
		public static void init(Context context) {
			if (null == mToast) {
				mToast = new CustomToast(context);
			}
		}

		private Handler mHandler = new Handler(Looper.getMainLooper()) {

			public void handleMessage(android.os.Message msg) {
				int what = msg.what;
				switch (what) {
				case FLAG_SHOW:
					String str = msg.obj.toString();
					if (!TextUtils.isEmpty(str)) {
						showMsg(str);
					}
					break;
				case FLAG_HIDE:
					hideMsg();
					break;
				case FLAG_CLEAR:
					showMsg("�����쳣����Ϣ̫��");
					break;

				default:
					break;
				}
			};
		};

		private void showMsg(String msg) {
			try {
				tvAlert.setText(msg);
				if (null == toastView.getParent()) {
					mWindowManager.addView(toastView, mParams);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void hideMsg() {
			try {
				if (null != toastView.getParent()) {
					mWindowManager.removeView(toastView);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * ��ʾ��Ϣ
		 * 
		 * @param msg
		 *            ��ʾ������
		 */
		public static void show(String msg) {
			try {
				mMsgQueue.put(msg); // block
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void show(Context context, int id) {
			try {
				mMsgQueue.put(context.getResources().getString(id)); // block
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * �˳�
		 */
		public static void eixt() {
			try {
				mMsgQueue.put(QUITMSG);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void execute() {
			try {
				if(null == mMsgQueue)mMsgQueue = new ArrayBlockingQueue<String>(QUEUE_SIZE);
				String msgStr = mMsgQueue.take();
				
				if (QUITMSG.equals(msgStr)) {
					exitToast();
					return;
				}

				Message msg = mHandler.obtainMessage();
				if (null == msg) {
					msg = new Message();
				}
				msg.what = FLAG_SHOW;
				msg.obj = msgStr;
				mHandler.sendMessage(msg);

				Thread.sleep(SHOW_TIME);

				if (mMsgQueue.size() == 0) {
					mHandler.sendEmptyMessage(FLAG_HIDE);
				}

				if (mMsgQueue.size() > QUEUE_SIZE_LIMIT) {
					mMsgQueue.clear();

					mHandler.sendEmptyMessage(FLAG_CLEAR);
					Thread.sleep(SHOW_TIME);
					mHandler.sendEmptyMessage(FLAG_HIDE);
				}

				System.out.println(">>>>>" + mMsgQueue.size());
			} catch (Exception e) {
				e.printStackTrace();
				mHandler.sendEmptyMessage(FLAG_HIDE);
			}
		}

		/**
		 * �˳��������ڴ�
		 */
		private void exitToast() {
			try {
				hideMsg();

				quit();
				mHandler.removeCallbacksAndMessages(null);
				mMsgQueue.clear();
				mMsgQueue = null;
				mToast = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}
