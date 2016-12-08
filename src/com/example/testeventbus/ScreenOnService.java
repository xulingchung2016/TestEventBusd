package com.example.testeventbus;


import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

@SuppressWarnings("deprecation")
public class ScreenOnService extends Service {

	// 键盘管理�?
	KeyguardManager mKeyguardManager;
	// 键盘�?
	private KeyguardLock mKeyguardLock;
	
	// 电源管理�?
	private PowerManager mPowerManager;
	// 唤醒�?
	private PowerManager.WakeLock mWakeLock;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		System.out.println("----> �?启服�?");
		mPowerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		mKeyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
		super.onCreate();
	}
	/**
	 * 获取电源锁，保持该服务在屏幕熄灭时仍然获取CPU时，保持运行
	 */
	private void acquireWakeLock() {
		if (null == mWakeLock) {
			PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
			mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK
					| PowerManager.ON_AFTER_RELEASE, getClass()
					.getCanonicalName());
			if (null != mWakeLock) {
//				Log.i(TAG, "call acquireWakeLock");
				mWakeLock.acquire();
			}
		}
	}

	// 释放设备电源�?
	private void releaseWakeLock() {
		if (null != mWakeLock && mWakeLock.isHeld()) {
//			Log.i(TAG, "call releaseWakeLock");
			mWakeLock.release();
			mWakeLock = null;
		}
	}
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.i("splash", "�����˷��񡷡�������������������������������");
		// 点亮亮屏
		mWakeLock = mPowerManager.newWakeLock(
				PowerManager.ACQUIRE_CAUSES_WAKEUP
						| PowerManager.SCREEN_DIM_WAKE_LOCK, "com.odier.mobile.ScreenOnService");
		mWakeLock.acquire();
		// 初始化键盘锁
		mKeyguardLock = mKeyguardManager.newKeyguardLock("");
		// 键盘解锁
		mKeyguardLock.disableKeyguard();
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("splash", "�����˷��񡷡�������������������������������");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		super.onDestroy();
		if (mWakeLock != null) {
			System.out.println("----> 终止服务,释放唤醒�?");
			mWakeLock.release();
			mWakeLock = null;
		}
		/*
		 * if (mKeyguardLock!=null) { System.out.println("----> 终止服务,重新锁键�?");
		 * mKeyguardLock.reenableKeyguard(); }
		 */
	}

}
