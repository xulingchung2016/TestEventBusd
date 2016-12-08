package com.example.testeventbus;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.widget.Toast;

public class TestJobService extends JobService {
	
	@Override
	public boolean onStartJob(JobParameters arg0) {
//		while(true){
		Toast.makeText(this, arg0.getJobId()+"", 1).show();
//		startService(new Intent(this, ScreenOnService.class));
		acquireWakeLock();
		doTask();
//	}
		return true;
	}
	private WakeLock mWakeLock;
	/**
	 * 获取电源锁，保持该服务在屏幕熄灭时仍然获取CPU时，保持运行
	 */
	private void acquireWakeLock() {
		if (null == mWakeLock) {
			PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
			mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK 
					| PowerManager.ON_AFTER_RELEASE, getClass()
					.getCanonicalName());
			if (null != mWakeLock) {
//				Log.i(TAG, "call acquireWakeLock");
				mWakeLock.acquire();
			}
		}
	}

	// 释放设备电源锁
	private void releaseWakeLock() {
		if (null != mWakeLock && mWakeLock.isHeld()) {
//			Log.i(TAG, "call releaseWakeLock");
			mWakeLock.release();
			mWakeLock = null;
		}
	}
	private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	private String log ="start\n";
	private int j;
	private void doTask() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					j++;
					acquireWakeLock();
					String time = formatter.format(new Date());
//					startService(new Intent(TestJobService.this, ScreenOnService.class));
					 Log.i("jobservice", "onStopJob:>>>>>>>>>>>" );
					 log += "onStopJob:>>>>>>>>>>> "+time+" j:> "+j;
				}
			}
		}).start();
		
	}

	private void startService(ScreenOnService intent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onStopJob(JobParameters arg0) {
		   Log.i("jobservice", "onStopJob:" + arg0.getJobId());

		return false;
	}

}
