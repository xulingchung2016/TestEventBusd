package com.example.testeventbus.cache;

import android.graphics.drawable.Drawable;

public class DrawableCache extends LruCache<String, Drawable> {
	private static final int DEFAULT_CAPACITY = 10;

	public DrawableCache(int capacity) {
		super(capacity);
	}

	public DrawableCache() {
		this(DEFAULT_CAPACITY);
	}

	public boolean isCached(String path) {
		return get(path) != null;
	}
}
