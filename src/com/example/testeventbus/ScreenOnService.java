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

	// 閿洏绠＄悊鍣?
	KeyguardManager mKeyguardManager;
	// 閿洏閿?
	private KeyguardLock mKeyguardLock;
	
	// 鐢垫簮绠＄悊鍣?
	private PowerManager mPowerManager;
	// 鍞ら啋閿?
	private PowerManager.WakeLock mWakeLock;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		System.out.println("----> 寮?鍚湇鍔?");
		mPowerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		mKeyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
		super.onCreate();
	}
	/**
	 * 鑾峰彇鐢垫簮閿侊紝淇濇寔璇ユ湇鍔″湪灞忓箷鐔勭伃鏃朵粛鐒惰幏鍙朇PU鏃讹紝淇濇寔杩愯
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

	// 閲婃斁璁惧鐢垫簮閿?
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
		Log.i("splash", "启动了服务》》》》》》》》》》》》》》》》》");
		// 鐐逛寒浜睆
		mWakeLock = mPowerManager.newWakeLock(
				PowerManager.ACQUIRE_CAUSES_WAKEUP
						| PowerManager.SCREEN_DIM_WAKE_LOCK, "com.odier.mobile.ScreenOnService");
		mWakeLock.acquire();
		// 鍒濆鍖栭敭鐩橀攣
		mKeyguardLock = mKeyguardManager.newKeyguardLock("");
		// 閿洏瑙ｉ攣
		mKeyguardLock.disableKeyguard();
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("splash", "启动了服务》》》》》》》》》》》》》》》》》");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		super.onDestroy();
		if (mWakeLock != null) {
			System.out.println("----> 缁堟鏈嶅姟,閲婃斁鍞ら啋閿?");
			mWakeLock.release();
			mWakeLock = null;
		}
		/*
		 * if (mKeyguardLock!=null) { System.out.println("----> 缁堟鏈嶅姟,閲嶆柊閿侀敭鐩?");
		 * mKeyguardLock.reenableKeyguard(); }
		 */
	}

}
