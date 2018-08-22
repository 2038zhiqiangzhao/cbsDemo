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
import com.people2000.common.cache.storage.RedisCacheStorage;
import com.people2000.common.cache.storage.exception.KeyIsNotFoundException;
import com.people2000.common.cache.storage.exception.RedisConnectionException;
import com.people2000.common.cache.storage.exception.ValueIsBlankException;
import com.people2000.common.cache.storage.exception.ValueIsNullException;

/**
 * 
 * TODO LRUcache， 依赖redis内部的LRU策略
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:Administrator,date:2012-9-18 下午2:52:03,content:TODO
 * </p>
 * 
 * @author Administrator
 * @date 2012-9-18 下午2:52:03
 * @since
 * @version
 */
@Deprecated
public abstract class DefaultLRURedisCache<K, V> implements
		RefreshableCache<K, V>, InitializingBean, DisposableBean {

	private static final Log LOG = LogFactory
			.getLog(DefaultLRURedisCache.class);

	private ILazyCacheProvider<K, V> cacheProvider;

	private RedisCacheStorage<K, V> cacheStorage;
	/**
	 * 读取时间堆栈
	 */
	private Map<K, Long> readTimeMap = new ConcurrentHashMap<K, Long>();
	/**
	 * 超时时间,单位秒,默认30分钟
	 */
	private int timeOut = 30 * 60 * 1000;

	/**
	 * 最大容量，默认100000
	 */
	private int maxSize = 100000;

	/**
	 * 数据提供者provider提供的数据最后修改时间,作为刷新缓存的时间戳
	 */
	private Date modifyTime;

	/**
	 * 自动刷新线程
	 */
	private ReloadThread thread;

	/**
	 * 自动刷新间隔时间,单位秒,默认15分钟
	 */
	private long interval = 15L * 60 * 1000;

	public void setInterval(int seconds) {
		this.interval = (long) seconds * 1000;
	}

	public void setTimeOut(int seconds) {
		this.timeOut = seconds * 1000;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public void setCacheProvider(ILazyCacheProvider<K, V> cacheProvider) {
		this.cacheProvider = cacheProvider;
	}

	public void setCacheStorage(RedisCacheStorage<K, V> cacheStorage) {
		this.cacheStorage = cacheStorage;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		CacheManager.getInstance().registerCacheProvider(this);
		modifyTime = this.cacheProvider.getLastModifyTime();
		// 启动刷新线程
		thread = new ReloadThread("LRU_REDIS_CACHE_RELOAD" + this.getUUID());
		thread.setDaemon(true);
		thread.start();
	}

	@Override
	public V get(K key) {
		V value = null;
		try {
			value = cacheStorage.hget(getUUID(), key);
		} catch (ValueIsBlankException e) {
			// key存在，value为空串
			return null;
		} catch (ValueIsNullException ex) {
			// key存在，value为null
			return null;
		} catch (KeyIsNotFoundException ex1) {
			// key不存在
			value = cacheProvider.get(key);
			cacheStorage.hset(getUUID(), key, value);
		} catch (RedisConnectionException exx) {
			// redis 连接出现异常
			return cacheProvider.get(key);
		}
		updateReadTime(key);
		clearTimeOutAndOverSizeKeys();
		return value;
	}

	@Override
	public Map<K, V> get() {
		throw new RuntimeException("LRUCache cannot get all!");
	}

	/**
	 * <p>
	 * 更新读取时间
	 * </p>
	 * 
	 * @author ningyu
	 * @date 2012-10-25 上午8:39:26
	 * @param key
	 * @see
	 */
	private void updateReadTime(K key) {
		this.readTimeMap.put(key, System.currentTimeMillis());
	}

	/**
	 * <p>
	 * 如果容量超过最大容量时，清除长时间没有访问的缓存数据
	 * </p>
	 * 
	 * @author ningyu
	 * @date 2012-10-25 上午8:42:29
	 * @see
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
		if (readTimeMap.size() > maxSize) {
			// 如果Cache中的数据量过大，则需要按照读取的先后顺序剔除较早以前访问的记录，因此首先对访问时间按照由小到大的顺序进行排序，获取剔除的比较时间
			List<Long> timeList = new ArrayList<Long>(tempMap.values());
			Collections.sort(timeList);
			// 小于这个时间的都是应该剔除的
			int index = tempMap.size() - maxSize - 1;
			long divtime = timeList.get(index);
			for (Map.Entry<K, Long> entry : tempMap.entrySet()) {
				Long putTime = entry.getValue();
				// 移除超时的记录
				if (divtime > putTime) {
					readTimeMap.remove(entry.getKey());
					cacheStorage.hremove(getUUID(), entry.getKey());
				}
			}
		}
	}

	@Override
	public void destroy() throws Exception {
	}

	@Override
	public boolean refresh() {
		Date lastTime = cacheProvider.getLastModifyTime();
		if (modifyTime != null && lastTime != null
				&& lastTime.after(modifyTime)) {
			// 获取所有更新的数据并更新到缓存
			Map<K, V> updateMap = cacheProvider.getUpdateObjectMaps(modifyTime);
			for (Map.Entry<K, V> entry : updateMap.entrySet()) {
				if (!cacheStorage.hset(getUUID(), entry.getKey(),
						entry.getValue())) {
					return false;
				}
			}
			modifyTime = lastTime;
		}
		return true;
	}

	@Override
	public boolean refresh(K... keys) {
		Map<K, V> updateMap = cacheProvider.getUpdateObjectMaps(keys);
		if (updateMap != null) {
			for (Map.Entry<K, V> entry : updateMap.entrySet()) {
				if (!cacheStorage.hset(getUUID(), entry.getKey(),
						entry.getValue())) {
					return false;
				}
			}
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
					boolean b = refresh();
					if (LOG.isDebugEnabled()) {
						if (b) {
							LOG.debug(this.getName() + ":刷新成功");
						} else {
							LOG.debug(this.getName() + ":刷新失败");
						}
					}
				} catch (Exception ex) {
					LOG.error(ex.getMessage());
				}
			}
		}
	}

	public void invalid() {
		cacheStorage.hremove(getUUID());
	}

	public void invalid(K key) {
		cacheStorage.hremove(getUUID(), key);
	}

	public void invalidMulti(K... keys) {
		cacheStorage.hremoveMulti(getUUID(), keys);
	}

	// public Boolean exists(K key) {
	// return cacheStorage.hexists(getUUID(), key);
	// }

}
