package com.example.testeventbus;

import java.io.File;

import com.example.testeventbus.disklrucache.DiskLruCache;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

public class DiskCacheManager {
	/*private DiskLruCache mDiskCache; 
	private Activity context;
	private static final int DISK_CACHE_SIZE = 1024 * 1024 * 10; // 10MB 
	private static final String DISK_CACHE_SUBDIR = "thumbnails"; 
	public void getInstance(Activity context){
		File cacheDir = getCacheDir(context, DISK_CACHE_SUBDIR); 
	    mDiskCache = DiskLruCache.openCache(context, cacheDir, DISK_CACHE_SIZE); 
	}
	
	public void addBitmapToCache(String key, Bitmap bitmap) { 
	    // Add to memory cache as before 
	    if (getBitmapFromMemCache(key) == null) { 
	        mMemoryCache.put(key, bitmap); 
	    }                                
	    // Also add to disk cache 
	    if (!mDiskCache.containsKey(key)) { 
	        mDiskCache.put(key, bitmap); 
	    } 
	}             
	
	public Bitmap getBitmapFromDiskCache(String key) { 
	    return mDiskCache.get(key); 
	}                                
	// Creates a unique subdirectory of the designated app cache directory. Tries to use external 
	// but if not mounted, falls back on internal storage. 
	public static File getCacheDir(Context context, String uniqueName) { 
	    // Check if media is mounted or storage is built-in, if so, try and use external cache dir 
	    // otherwise use internal cache dir 
	    final String cachePath = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED 
	            || !Environment.isExternalStorageRemovable() ? 
	                    context.getExternalCacheDir().getPath() : context.getCacheDir().getPath(); 
	    return new File(cachePath + File.separator + uniqueName); 
	}
*/
	
}
