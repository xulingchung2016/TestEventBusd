package com.example.testeventbus;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class ChangeService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onStart(Intent intent, int startId) {
		Toast.makeText(this, "laile3", 1).show();
		Log.i("changeService", "changeService>>>>>>>>");
		super.onStart(intent, startId);
		
	}
	@Override
	public void onCreate() {
		
		super.onCreate();
		
		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "laile3", 1).show();
		Log.i("changeService", "changeService>>>>>>>>");
		return super.onStartCommand(intent, flags, startId);
	}

}
