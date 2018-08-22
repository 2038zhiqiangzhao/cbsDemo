package com.people2000.common.cache;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.people2000.common.cache.provider.ITTLCacheProvider;
import com.people2000.common.cache.storage.RedisCacheStorage;
import com.people2000.common.cache.storage.exception.KeyIsNotFoundException;
import com.people2000.common.cache.storage.exception.RedisConnectionException;
import com.people2000.common.cache.storage.exception.ValueIsBlankException;
import com.people2000.common.cache.storage.exception.ValueIsNullException;

/**
 * TTL类型的缓存
 * 
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:ningyu,date:2013-1-30 下午5:28:32,content:TODO
 * </p>
 * 
 * @author ningyu
 * @date 2013-1-30 下午5:28:32
 * @since
 * @version
 */
public abstract class DefaultTTLRedisCache<V> implements ICache<String, V>,
		InitializingBean, DisposableBean {

	/**
	 * 日志类
	 */
	private static final Log LOG = LogFactory
			.getLog(DefaultTTLRedisCache.class);

	/**
	 * 数据提供者
	 */
	protected ITTLCacheProvider<V> cacheProvider;

	/**
	 * 数据存储器
	 */
	protected RedisCacheStorage<String, V> cacheStorage;

	/**
	 * 超时时间,单位秒,默认30分钟
	 */
	protected int timeOut = 30 * 60;

	/**
	 * 设置数据提供者
	 * 
	 * @author ningyu
	 * @date 2013-1-31 上午11:25:38
	 * @param cacheProvider
	 * @see
	 */
	public void setCacheProvider(ITTLCacheProvider<V> cacheProvider) {
		this.cacheProvider = cacheProvider;
	}

	/**
	 * 设置数据存储者
	 * 
	 * @author ningyu
	 * @date 2013-1-31 上午11:24:03
	 * @param cacheStorage
	 * @see
	 */
	public void setCacheStorage(RedisCacheStorage<String, V> cacheStorage) {
		this.cacheStorage = cacheStorage;
	}

	/**
	 * 设置超时时间
	 * 
	 * @author ningyu
	 * @date 2013-1-31 上午11:23:51
	 * @param seconds
	 * @see
	 */
	public void setTimeOut(int seconds) {
		this.timeOut = seconds;
	}

	/**
	 * 根据uuid和key生成key
	 * 
	 * @author ningyu
	 * @date 2013-1-31 上午11:23:35
	 * @param key
	 * @return
	 * @see
	 */
	protected String getKey(String key) {
		return getUUID() + "_" + key;
	}

	/**
	 * 获取数据 如果返回null就是真的没有数据，无需再去数据库再取
	 * 
	 * @author ningyu
	 * @date 2013-1-31 上午11:25:55
	 * @param key
	 * @return
	 * @see com.deppon.foss.framework.cache.ICache#get(java.lang.Object)
	 */
	@Override
	public V get(String key) {
		if (StringUtils.isBlank(key)) {
			throw new RuntimeException("key does not allow for null!");
		}
		V value = null;
		try {
			value = cacheStorage.get(getKey(key));
		} catch (ValueIsBlankException e) {
			LOG.warn("缓存[" + getUUID() + "]，key[" + key
					+ "]存在，value为空串，返回结果[null]");
			// key存在，value为空串
			return null;
		} catch (ValueIsNullException ex) {
			// key存在，value为null
			LOG.warn("缓存[" + getUUID() + "]，key[" + key
					+ "]存在，value为null，返回结果[null]");
			return null;
		} catch (KeyIsNotFoundException ex1) {
			// key不存在
			value = cacheProvider.get(key);
			LOG.warn("缓存[" + getUUID() + "]，key[" + key + "]不存在，走数据库查询，返回结果["
					+ value + "]");
			cacheStorage.set(getKey(key), value, timeOut);
		} catch (RedisConnectionException exx) {
			// redis 连接出现异常
			value = cacheProvider.get(key);
			LOG.warn("redis连接出现异常，走数据库查询!");
			return value;
		}
		return value;
	}

	@Override
	public Map<String, V> get() {
		throw new RuntimeException(getUUID() + ":TTLCache cannot get all!");
	}

	@Override
	public void invalid() {
		throw new RuntimeException(getUUID() + ":TTLCache cannot invalid all!");
	}

	/**
	 * 失效数据
	 * 
	 * @author ningyu
	 * @date 2013-1-31 上午11:26:50
	 * @param key
	 * @see com.deppon.foss.framework.cache.ICache#invalid(java.lang.Object)
	 */
	@Override
	public void invalid(String key) {
		cacheStorage.remove(getKey(key));
	}

	@Override
	public void invalidMulti(String... keys) {
		if (keys == null)
			return;
		String[] skeys = new String[keys.length];
		for (int i = 0; i < keys.length; i++) {
			skeys[i] = getKey(keys[i]);
		}
		cacheStorage.removeMulti(skeys);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void destroy() throws Exception {
		CacheManager.getInstance().unregisterCacheProvider(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void afterPropertiesSet() throws Exception {
		CacheManager.getInstance().registerCacheProvider(this);
	}

}
