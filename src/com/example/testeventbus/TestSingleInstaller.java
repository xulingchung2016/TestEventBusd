package com.example.testeventbus;

import java.io.File;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TestSingleInstaller extends Activity {
	private Button btn_install;
	private String saveFileName="";
	private Context context;
	 private final String PACKAGE_NAME = "com.example.testeventbus";  
	    private final String SERVICE_NAME = "com.example.testeventbus.ApkService";  
	    
	    private final String ACTION_ALERT_DIALOG = "com.corget.over.dialog";  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.test_singleinstall);
		context = this;
		btn_install = (Button) findViewById(R.id.button1);
		saveFileName = Environment.getExternalStorageDirectory().getAbsolutePath()+"/NaviDemo.apk";
		Log.i("savePaht", saveFileName);
	}
	
	public void install(View v){
		if(!isAccessibilitySettingsOn(context)){
			popOpenAlertDialog(ACTION_ALERT_DIALOG);
			return;
		}
		
		File apkfile = new File(saveFileName);
		if (!apkfile.exists()) {
			return;
		}
		
		/*Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		startActivity(i);*/
		
		 Intent localIntent = new Intent(Intent.ACTION_VIEW);
		    localIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		    Uri uri;
		    /**
		      * Android7.0+禁止应用对外暴露file://uri，改为content://uri；具体参考FileProvider
		      */
		    if (Build.VERSION.SDK_INT >= 24) {
		        uri = FileProvider.getUriForFile(this, "com.science.fileprovider", apkfile);
		        localIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		    } else {
		        uri = Uri.fromFile(apkfile);
		    }
		    localIntent.setDataAndType(uri, "application/vnd.android.package-archive"); //打开apk文件
		    startActivity(localIntent);
	}

	
	 public  boolean isAccessibilitySettingsOn(Context mContext) {  
	        boolean isAccessibilityOn = false;  
	        try {  
	            int accessibilityEnabled = Settings.Secure.getInt(mContext.getContentResolver(),Settings.Secure.ACCESSIBILITY_ENABLED);  
	            TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');  
	            if (accessibilityEnabled == 1) {  
	                String settingValue = Settings.Secure.getString(mContext.getContentResolver(),Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);  
	                if (settingValue != null) {  
	                    TextUtils.SimpleStringSplitter splitter = mStringColonSplitter;  
	                    splitter.setString(settingValue);  
	                    while (splitter.hasNext()) {  
	                        String accessabilityService = splitter.next();  
	    //辅助服务关键路径，格式如"com.corget/com.corget.service.UnAccessibilityService"  
	                        String service = PACKAGE_NAME+"/"+SERVICE_NAME;  
	                        if (accessabilityService.equalsIgnoreCase(service)) {  
	                            isAccessibilityOn = true;  
	                        }  
	                    }  
	                }  
	            }   
	        } catch (SettingNotFoundException e) {  
	            e.printStackTrace();  
	        }  
	        return isAccessibilityOn;  
	    }  
	    
	    public  void popOpenAlertDialog(final String alertBroadcastAction) {  
	        Builder mDialogBuilder = new AlertDialog.Builder(this);  
	        AlertDialog dialog = null;  
	        mDialogBuilder.setIcon(R.drawable.ic_launcher);  
	        mDialogBuilder.setTitle("警告");  
	        mDialogBuilder.setMessage("检测到辅助服务没有开启，马上前往设置？");  
	        mDialogBuilder.setCancelable(false);  
	        mDialogBuilder.setPositiveButton("前往",  
	                new DialogInterface.OnClickListener() {  
	                    @Override  
	                    public void onClick(DialogInterface dialog, int which) {  
	                        dialog.dismiss();  
	                        // 前往辅助服务设置页面  
	                        Intent startIntent = new Intent(  
	                                Settings.ACTION_ACCESSIBILITY_SETTINGS);  
	                        context.startActivity(startIntent);  
	                        // 发送一个广播，以便弹出"开启服务"提示对话框  
	                        context.sendBroadcast(new Intent()  
	                                .setAction(alertBroadcastAction));  
	                    }  
	                });  
	        mDialogBuilder.setNegativeButton("不了",  
	                new DialogInterface.OnClickListener() {  
	                    @Override  
	                    public void onClick(DialogInterface dialog, int which) {  
	                            dialog.dismiss();  
	                    }  
	                });  
	        dialog = mDialogBuilder.create();  
	        dialog.show();  
	    }  
}
