package com.example.testeventbus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import dalvik.system.DexClassLoader;

public class MyApp extends Application {
	
	public static RefWatcher getRefWatcher(Context context) {
		MyApp application = (MyApp) context
				.getApplicationContext();
		return application.refWatcher;
	}

	private RefWatcher refWatcher;

	@Override
	public void onCreate() {
		super.onCreate();
//		dexTool();
		refWatcher = LeakCanary.install(this);
	}
	
	 @SuppressLint("NewApi")
	    private void dexTool() {
	           File dexDir = new File(getFilesDir(), "dlibs");
	           dexDir.mkdir();
	           File dexFile = new File(dexDir, "libs.apk");
	           File dexOpt = getCacheDir();
	           try{
	                  InputStream ins = getAssets().open("libs.apk");
	                  if(dexFile.length() != ins.available()) {
	                         FileOutputStream fos = new FileOutputStream(dexFile);
	                         byte[]buf = new byte[4096];
	                         int l;
	                         while((l = ins.read(buf)) != -1) {
	                                fos.write(buf,0, l);
	                         }
	                         fos.close();
	                  }
	                  ins.close();
	           }catch (Exception e) {
	                  throw new RuntimeException(e);
	           }
	   
	           ClassLoader cl = getClassLoader();
	           ApplicationInfo ai = getApplicationInfo();
	           String nativeLibraryDir = null;
	           if(Build.VERSION.SDK_INT > 8) {
	                  nativeLibraryDir= ai.nativeLibraryDir;
	           }else {
	                  nativeLibraryDir= "/data/data/" + ai.packageName + "/lib/";
	           }
	           DexClassLoader dcl = new DexClassLoader(dexFile.getAbsolutePath(),
	                         dexOpt.getAbsolutePath(),nativeLibraryDir, cl.getParent());
	   
	           try{
	                  Field f = ClassLoader.class.getDeclaredField("parent");
	                  f.setAccessible(true);
	                  f.set(cl,dcl);
	           }catch (Exception e) {
	                  throw new RuntimeException(e);
	           }
	    }
	    
	

}
