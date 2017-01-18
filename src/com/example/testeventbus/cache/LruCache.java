package com.example.testeventbus.cache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache<K, V> {

	// mLruMap：一个简单实现LRU算法的LinkedHashMap
	private final HashMap<K, V> mLruMap;

	// mWeakMap：保�? 弱引用对象Entry
	private final HashMap<K, Entry<K, V>> mWeakMap = new HashMap<K, Entry<K, V>>();

	// mQueue：记录引用对象已被垃圾回收器回收过的Entry
	private ReferenceQueue<V> mQueue = new ReferenceQueue<V>();

	// 设定LruCache固定存储的最大容�?
	@SuppressWarnings("serial")
	public LruCache(final int capacity) {
		mLruMap = new LinkedHashMap<K, V>(16, 0.75f, true) {
			@Override
			protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
				// LRU算法�?关键的一步，自动清除过期数据,始终保证mLruMap 的size<=capacity，即mLruMap
				// 内存占用总大�?<=单个对象�?占内�?*capacity
				return size() > capacity;
			}
		};
	}

	private static class Entry<K, V> extends WeakReference<V> {
		// 保存Key值，以便mWeakMap删除已被垃圾回收器回收过的弱引用对象Entry
		K mKey;

		public Entry(K key, V value, ReferenceQueue<V> queue) {
			super(value, queue);
			mKey = key;
		}
	}

	// 清空mWeakMap里已被垃圾回收器回收过的Entry（表明Entry�?引用的对象已经被垃圾回收器回收，这时�?要清除Entry对象本身�?
	@SuppressWarnings("unchecked")
	private void cleanUpWeakMap() {
		Entry<K, V> entry = (Entry<K, V>) mQueue.poll();
		// �?旦垃圾回收器回收该Entry�?引用的对象，就从mWeakMap里删除该Entry
		while (entry != null) {
			mWeakMap.remove(entry.mKey);
			entry = (Entry<K, V>) mQueue.poll();
		}
	}

	// 将对象放入缓存，并保存弱引用
	public synchronized V put(K key, V value) {
		cleanUpWeakMap();
		mLruMap.put(key, value);
		Entry<K, V> entry = mWeakMap.put(key, new Entry<K, V>(key, value,
				mQueue));
		return entry == null ? null : entry.get();
	}

	// 取得对象
	public synchronized V get(K key) {
		cleanUpWeakMap();
		V value = mLruMap.get(key);
		if (value != null)
			return value;
		Entry<K, V> entry = mWeakMap.get(key);
		return entry == null ? null : entry.get();
	}

	// 清空
	public synchronized void clear() {
		mLruMap.clear();
		mWeakMap.clear();
		mQueue = new ReferenceQueue<V>();
	}
}