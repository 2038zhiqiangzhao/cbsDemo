package com.people2000.common.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.people2000.common.cache.provider.ILazyCacheProvider;
import com.people2000.common.cache.storage.LRUCacheStorage;

/**
 * 
 * 
 *******************************************
 * <b style="font-family:微软雅黑"><small>Description:LRU缓存默认实现</small></b> </br> <b
 * style="font-family:微软雅黑"><small>HISTORY</small></b></br> <b
 * style="font-family:微软雅黑"><small> ID DATE PERSON REASON</small></b><br>
 ********************************************
 * <div style="font-family:微软雅黑,font-size:70%"> 1 2011-4-14 樊斌 新增 </div>
 ********************************************
 */
@Deprecated
public abstract class DefaultLRUCache<K, V> implements RefreshableCache<K, V>,
		InitializingBean, DisposableBean {

	private static final Log LOG = LogFactory.getLog(DefaultLRUCache.class);

	private ILazyCacheProvider<K, V> cacheProvider;

	/**
	 * 缓存对象最大个数
	 */
	private int maxSize = 100000;

	/**
	 * 缓存超时时间，单位秒，默认30分钟
	 */
	private long timeOut = 30L * 60 * 1000;

	/**
	 * 自动刷新缓存间隔时间，单位秒，默认15分钟
	 */
	private long interval = 15L * 60 * 1000;

	@SuppressWarnings("unused")
	private int maxFactor = (int) (maxSize * 1.2);

	private LRUCacheStorage<K, V> cacheStorage;

	/**
	 * 缓存读取时间存储对象
	 */
	private Map<K, Long> readTimeMap;

	/**
	 * 自动刷新缓存线程
	 */
	private ReloadThread thread;

	/**
	 * 数据提供者provider提供的数据最后修改时间,作为刷新缓存的时间戳
	 */
	private Date modifyTime;

	// private Map<K, Object> syncKeyMap;

	/**
	 * 
	 * 创建一个新的实例 CacheManager.
	 * 
	 * @since 0.6
	 */
	public DefaultLRUCache() {
		// this.syncKeyMap = new ConcurrentHashMap<K, Object>();
		this.readTimeMap = new ConcurrentHashMap<K, Long>();
		this.cacheStorage = new LRUCacheStorage<K, V>();
	}

	public void setCacheProvider(ILazyCacheProvider<K, V> cacheProvider) {
		this.cacheProvider = cacheProvider;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
		maxFactor = (int) (maxSize * 1.2);
	}

	public void setTimeOut(int seconds) {
		this.timeOut = (long) seconds * 1000;
	}

	public void setInterval(int seconds) {
		this.interval = (long) seconds * 1000;
	}

	/**
	 * 先从store中读取，没有再从provider中取，同时记录读取的时间
	 * 
	 * @see com.deppon.foss.framework.cache.ICache#get(java.lang.Object) get
	 * @param key
	 * @return
	 * @since: 0.6
	 */
	@Override
	public final V get(K key) {
		V value = cacheStorage.get(key);
		if (value == null) {
			value = cacheProvider.get(key);
			if (value != null) {
				cacheStorage.set(key, value);
			}
		}
		if (value != null) {
			updateReadTime(key);
		}
		return value;
	}

	private void updateReadTime(K key) {
		this.readTimeMap.put(key, System.currentTimeMillis());
		// if (readTimeMap.size() > maxFactor) {
		// this.clearTimeOutAndOverSizeKeys();
		// }
	}

	// private Object getSyncKey(K key) {
	// Object syncKey = this.syncKeyMap.get(key);
	// if (syncKey == null) {
	// synchronized (syncKeyMap) {
	// syncKey = this.syncKeyMap.get(key);
	// if (syncKey == null) {
	// syncKey = new Object();
	// syncKeyMap.put(key, syncKey);
	// }
	// }
	// }
	// return syncKey;
	// }

	/**
	 * LRU缓存禁止取出所有数据
	 */
	@Override
	public Map<K, V> get() {
		throw new RuntimeException("LRUCache cannot get all!");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void afterPropertiesSet() throws Exception {
		CacheManager.getInstance().registerCacheProvider(this);
		modifyTime = this.cacheProvider.getLastModifyTime();
		// 启动刷新线程
		thread = new ReloadThread("LRU_CACHE_RELOAD" + this.getUUID());
		thread.setDaemon(true);
		thread.start();
	}

	/**
	 * 清理过期缓存数据 clearTimeOutAndOverSizeKeys
	 * 
	 * @return void
	 * @since: 0.6
	 */
	private void clearTimeOutAndOverSizeKeys() {
		Map<K, Long> tempMap = new HashMap<K, Long>(readTimeMap);
		Long time = System.currentTimeMillis();
		// 比较时间，如果上次读取的时间小于这个时间，说明这条记录已经超时了
		long compareTime = time - timeOut;

		// 先移除超时的数据
		for (Map.Entry<K, Long> entry : tempMap.entrySet()) {
			Long putTime = entry.getValue();
			// 移除超时的记录
			if (compareTime > putTime) {
				readTimeMap.remove(entry.getKey());
				cacheStorage.remove(entry.getKey());
			}
		}
		if (tempMap.size() > maxSize) {
			// 如果Cache中的数据量过大，则需要按照读取的先后顺序剔除较早以前访问的记录，因此首先对访问时间按照由小到大的顺序进行排序，获取剔除的比较时间
			List<Long> timeList = new ArrayList<Long>(tempMap.values());
			Collections.sort(timeList);
			// 小于这个时间的都是应该剔除的
			long divtime = timeList.get(tempMap.size() - maxSize - 1);
			for (Map.Entry<K, Long> entry : tempMap.entrySet()) {
				Long putTime = entry.getValue();
				// 移除超时的记录
				if (divtime > putTime) {
					readTimeMap.remove(entry.getKey());
					cacheStorage.remove(entry.getKey());
				}
			}
		}
	}

	/**
	 * 应用关闭时终止刷新线程
	 * 
	 * @see org.springframework.beans.factory.DisposableBean#destroy() destroy
	 * @throws Exception
	 * @since:0.6
	 */
	@Override
	public void destroy() throws Exception {
		while (thread.state != 2) {
			synchronized (this) {
				this.wait(30l * 1000);
			}
		}
		thread.interrupt();
	}

	@Override
	public boolean refresh() {
		Date lastTime = cacheProvider.getLastModifyTime();
		if (modifyTime != null && lastTime != null
				&& lastTime.after(modifyTime)) {
			// 获取所有更新的数据并更新到缓存
			Map<K, V> updateMap = cacheProvider.getUpdateObjectMaps(modifyTime);
			for (Map.Entry<K, V> entry : updateMap.entrySet()) {
				cacheStorage.set(entry.getKey(), entry.getValue());
			}
			modifyTime = lastTime;
			return true;
		}
		return false;
	}

	@Override
	public boolean refresh(K... keys) {
		Map<K, V> updateMap = cacheProvider.getUpdateObjectMaps(keys);
		if (updateMap == null) {
			return false;
		}
		for (Map.Entry<K, V> entry : updateMap.entrySet()) {
			cacheStorage.set(entry.getKey(), entry.getValue());
		}
		return true;
	}

	private class ReloadThread extends Thread {
		// 0表示新生 1表示运行 2表示阻塞
		private volatile int state;

		ReloadThread(String threadName) {
			super(threadName);
		}

		@Override
		public void run() {
			while (true) {
				try {
					state = 2;
					Thread.sleep(interval);
				} catch (InterruptedException e) {
					LOG.error(e.getMessage());
					break;
				}
				try {
					state = 1;
					// 清理过期数据
					clearTimeOutAndOverSizeKeys();
					// 获取所有更新的数据并更新到缓存
					refresh();
				} catch (Exception ex) {
					LOG.error(ex.getMessage());
				}
			}
		}
	}

	public void invalid() {
		cacheStorage.clear();
	}

	public void invalid(K key) {
		cacheStorage.remove(key);
	}

	public void invalidMulti(K... keys) {
		for (int i = 0; i < keys.length; i++) {
			invalid(keys[i]);
		}
	}

	// public Boolean exists(K key) {
	// return cacheStorage.exists(key);
	// }
}
