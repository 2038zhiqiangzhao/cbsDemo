package com.people2000.common.cache.storage;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

import com.people2000.common.cache.CacheProxy;
import com.people2000.common.cache.CacheUtils;
import com.people2000.common.cache.redis.RedisClient;
import com.people2000.common.cache.storage.exception.KeyIsNotFoundException;
import com.people2000.common.cache.storage.exception.RedisCacheStorageException;
import com.people2000.common.cache.storage.exception.RedisConnectionException;
import com.people2000.common.cache.storage.exception.ValueIsBlankException;
import com.people2000.common.cache.storage.exception.ValueIsNullException;

public class RedisCacheProxy implements CacheProxy {

	private RedisClient client;

	/**
	 * 日志
	 */
	Log log = LogFactory.getLog(getClass());

	/**
	 * 默认数据过期时间
	 */
	private int expire = 3600 * 24;

	public void setClient(RedisClient client) {
		this.client = client;
	}

	/**
	 * <p>
	 * 存入数据，默认时效：3600 * 24
	 * </p>
	 * 
	 * @author ningyu
	 * @date 2012-10-22 下午5:24:47
	 * @param key
	 * @param value
	 * @return boolean 是否执行成功
	 * @see com.deppon.foss.framework.cache.IRemoteCacheStore#set(java.lang.Object,
	 *      java.lang.Object)
	 */
	@Override
	public boolean put(String key, Object value) {
		return put(key, value, expire);
	}

	/**
	 * <p>
	 * 存入有时效的数据
	 * </p>
	 * 
	 * @author ningyu
	 * @date 2012-10-22 下午5:23:59
	 * @param key
	 * @param value
	 * @param exp
	 * @return boolean 是否执行成功
	 * @see com.deppon.foss.framework.cache.IRemoteCacheStore#set(java.lang.Object,
	 *      java.lang.Object, int)
	 */
	@Override
	public boolean put(String key, Object value, int exp) {
		if (!client.getPoolInited()) {
			return false;
		}
		Jedis j = null;
		String skey = CacheUtils.toJsonString(key);
		;
		String svalue = CacheUtils.toJsonString(value);
		if (skey == null) {
			throw new RedisCacheStorageException("key does not allow for null!");
		}
		boolean borrowOrOprSuccess = true;
		try {
			j = client.getResource();
			j.setex(skey, exp, svalue);
		} catch (JedisException e) {
			if (e instanceof JedisConnectionException) {
				borrowOrOprSuccess = false;
				client.onError();
				if (j != null) {
					client.returnBrokenResource(j);
				}
			}
			log.error(e.getMessage(), e);
			return false;
		} finally {
			if (borrowOrOprSuccess) {
				// 返回到资源池
				client.returnResource(j);
			}
		}
		return true;
	}

	/**
	 * <p>
	 * 获取key对应的数据
	 * </p>
	 * 
	 * @author ningyu
	 * @date 2012-10-22 下午5:23:29
	 * @param key
	 * @return
	 * @see com.deppon.foss.framework.cache.IRemoteCacheStore#get(java.lang.Object)
	 */
	@Override
	public Object get(String key) {
		if (!client.getPoolInited()) {
			throw new RedisConnectionException("jedis pool is not init!");
		}
		Jedis j = null;
		String skey = CacheUtils.toJsonString(key);
		if (skey == null) {
			throw new RedisCacheStorageException("key does not allow for null!");
		}
		try {
			j = client.getResource();
			String re = j.get(skey);
			// 返回到资源池
			client.returnResource(j);
			if (re != null) {
				if (StringUtils.isBlank(re)) {
					// key存在，value为空串
					throw new ValueIsBlankException(
							"key exists, value is blank!");
				} else if (StringUtils.equalsIgnoreCase(re, "nil")) {
					// key不存在
					throw new KeyIsNotFoundException("key is not found!");
				} else if (StringUtils.equalsIgnoreCase(re, "null")) {
					// key存在，value为null
					throw new ValueIsNullException("key exists, value is null!");
				} else {
					return CacheUtils.jsonParseObject(re);
				}
			} else {
				// key不存在
				// throw new StringeyIsNotFoundException("key is not found!");
				return null;
			}
		} catch (JedisException e) {
			if (e instanceof JedisConnectionException) {
				client.onError();
				if (j != null) {
					client.returnBrokenResource(j);
				}
			}
			log.error(e.getMessage(), e);
			throw new RedisConnectionException(e);
		}
	}

	/**
	 * <p>
	 * 删除指定的缓存信息
	 * </p>
	 * 
	 * @author ningyu
	 * @date 2012-10-22 下午5:23:17
	 * @param key
	 * @see com.deppon.foss.framework.cache.IRemoteCacheStore#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(String key) {
		if (!client.getPoolInited()) {
			return false;
		}
		Jedis j = null;
		String skey = CacheUtils.toJsonString(key);
		if (skey == null) {
			throw new RedisCacheStorageException("key does not allow for null!");
		}
		boolean borrowOrOprSuccess = true;
		try {
			j = client.getResource();
			j.del(skey);
		} catch (JedisException e) {
			if (e instanceof JedisConnectionException) {
				borrowOrOprSuccess = false;
				client.onError();
				if (j != null) {
					client.returnBrokenResource(j);
				}
			}
			log.error(e.getMessage(), e);
		} finally {
			if (borrowOrOprSuccess) {
				client.returnResource(j);// 返回到资源池
			}
		}
		return borrowOrOprSuccess;
	}

	@Override
	public boolean put(String paramString, Object paramObject, Date paramDate) {
		Date now = new Date();
		long millis = paramDate.getTime() - now.getTime();
		if (millis < 0) {
			return false;
		}

		int second = (int) (millis / 1000);
		return put(paramString, paramObject, second);
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
