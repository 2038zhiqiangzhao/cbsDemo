package com.people2000.common.cache.storage;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LocalCacheStorage implements LocalCache {

	private static final Log Logger = LogFactory
			.getLog(LocalCacheStorage.class);

	ConcurrentHashMap<String, SoftReference<Object>>[] caches;
	ConcurrentHashMap<String, Long> expiryCache;

	private ScheduledExecutorService scheduleService;

	private int expiryInterval = 10;

	private int moduleSize = 10;

	public LocalCacheStorage() {
		init();
	}

	public LocalCacheStorage(int expiryInterval, int moduleSize) {
		this.expiryInterval = expiryInterval;
		this.moduleSize = moduleSize;
		init();
	}

	@SuppressWarnings("unchecked")
	private void init() {
		caches = new ConcurrentHashMap[moduleSize];

		for (int i = 0; i < moduleSize; i++) {
			caches[i] = new ConcurrentHashMap<String, SoftReference<Object>>();
		}

		expiryCache = new ConcurrentHashMap<String, Long>();

		scheduleService = Executors.newScheduledThreadPool(1);

		scheduleService.scheduleAtFixedRate(new CheckOutOfDateSchedule(caches,
				expiryCache), 0, expiryInterval * 60, TimeUnit.SECONDS);

		if (Logger.isInfoEnabled()) {
			Logger.info("LocalCacheStorage CheckService is start!");
		}
	}

	public boolean clear() {
		if (caches != null)
			for (ConcurrentHashMap<String, SoftReference<Object>> cache : caches) {
				cache.clear();
			}

		if (expiryCache != null) {
			expiryCache.clear();
		}

		return true;
	}

	public boolean containsKey(String key) {
		checkValidate(key);
		return getCache(key).containsKey(key);
	}

	public Object get(String key) {
		checkValidate(key);
		SoftReference<Object> sr = getCache(key).get(key);
		if (sr == null) {
			expiryCache.remove(key);
			return null;
		} else {
			return sr.get();
		}
	}

	public Set<String> keySet() {
		checkAll();
		return expiryCache.keySet();
	}

	public boolean put(String key, Object value) {
		SoftReference<Object> result = getCache(key).put(key,
				new SoftReference<Object>(value));
		expiryCache.put(key, -1L);

		return result == null;
	}

	public boolean put(String key, Object value, Date expiry) {
		SoftReference<Object> result = getCache(key).put(key,
				new SoftReference<Object>(value));
		expiryCache.put(key, expiry.getTime());

		return result == null;
	}

	public boolean remove(String key) {
		SoftReference<Object> result = getCache(key).remove(key);
		expiryCache.remove(key);
		return result == null;
	}

	public int size() {
		checkAll();

		return expiryCache.size();
	}

	public Collection<Object> values() {
		checkAll();

		Collection<Object> values = new ArrayList<Object>();

		for (ConcurrentHashMap<String, SoftReference<Object>> cache : caches) {
			for (SoftReference<Object> sr : cache.values()) {
				values.add(sr.get());
			}
		}

		return values;
	}

	private ConcurrentHashMap<String, SoftReference<Object>> getCache(String key) {
		long hashCode = (long) key.hashCode();

		if (hashCode < 0)
			hashCode = -hashCode;

		int moudleNum = (int) hashCode % moduleSize;

		return caches[moudleNum];
	}

	private void checkValidate(String key) {
		if (key != null && expiryCache.get(key) != null
				&& expiryCache.get(key) != -1
				&& new Date(expiryCache.get(key)).before(new Date())) {
			getCache(key).remove(key);
			expiryCache.remove(key);
		}
	}

	private void checkAll() {
		Iterator<String> iter = expiryCache.keySet().iterator();

		while (iter.hasNext()) {
			String key = iter.next();
			checkValidate(key);
		}
	}

	class CheckOutOfDateSchedule implements Runnable {
		ConcurrentHashMap<String, SoftReference<Object>>[] caches;
		ConcurrentHashMap<String, Long> expiryCache;

		public CheckOutOfDateSchedule(
				ConcurrentHashMap<String, SoftReference<Object>>[] caches,
				ConcurrentHashMap<String, Long> expiryCache) {
			this.caches = caches;
			this.expiryCache = expiryCache;
		}

		public void run() {
			check();
		}

		public void check() {
			try {
				for (ConcurrentHashMap<String, SoftReference<Object>> cache : caches) {
					Iterator<String> keys = cache.keySet().iterator();

					while (keys.hasNext()) {
						String key = keys.next();

						if (expiryCache.get(key) == null)
							continue;

						long date = expiryCache.get(key);

						if ((date > 0) && (new Date(date).before(new Date()))) {
							expiryCache.remove(key);
							cache.remove(key);
						}

					}

				}
			} catch (Exception ex) {
				Logger.info("LocalCacheStorage CheckService is start!");
			}
		}

	}

	@Override
	public boolean put(String key, Object value, int TTL) {
		SoftReference<Object> result = getCache(key).put(key,
				new SoftReference<Object>(value));

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, TTL);
		expiryCache.put(key, calendar.getTime().getTime());

		return result == null;
	}

	public void destroy() {
		try {
			clear();

			if (scheduleService != null)
				scheduleService.shutdown();

			scheduleService = null;
		} catch (Exception ex) {
			Logger.error(ex);
		}
	}

	@Override
	public boolean add(String paramString, Object paramObject) {
		return put(paramString, paramObject);
	}

	@Override
	public boolean add(String paramString, Object paramObject, int paramInt) {
		return put(paramString, paramObject, paramInt);
	}

}
