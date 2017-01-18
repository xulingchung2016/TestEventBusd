package com.example.testeventbus.cache;

import android.graphics.Bitmap;

public class BitmapCache extends LruCache<String, Bitmap> {

	private static final int DEFAULT_CAPACITY = 10;

	public BitmapCache(int capacity) {
		super(capacity);
	}

	public BitmapCache() {
		this(DEFAULT_CAPACITY);
	}

	public boolean isCached(String url) {
		return get(url) != null;
	}
}