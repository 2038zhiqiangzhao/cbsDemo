package com.people2000.user.client.utils;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.people2000.common.cache.CacheProxy;

/**
 * Created by steven.zhou on 2015/8/3.
 */
public class CacheCommUtils {

	private static final Log logger = LogFactory.getLog(SsoClientUtil.class);

	private static CacheProxy cache;

	// CLIENT配置文件路径（相对）
	private final static String CLIENT_FILE_PATH = "ouser/ouser-client/ouser_client_memcache.xml";

	private static synchronized void initCache() {
		if (cache == null) {
			// String dir = System.getProperty("global.config.path") +
			// "/ouser/ouser-client/ouser_client_memcache.xml";
			/*
			 * File clientFile = OccPropertiesLoaderUtils.getFile("ouser" ,
			 * CLIENT_FILE_PATH ); if (clientFile == null ||
			 * !clientFile.exists()){ clientFile = new
			 * File(System.getProperty("global.config.path") ,
			 * CLIENT_FILE_PATH); }
			 */
			File clientFile = new File(
					System.getProperty("global.config.path"), CLIENT_FILE_PATH);

			// logger.info("配置文件地址=" + dir);
			// File file = new File(dir);
			/*
			 * if (clientFile.exists()) { cache =
			 * MemcacheExtend.getInstance("file:" +
			 * clientFile.getAbsolutePath()).getCacheProxy("user"); if (cache ==
			 * null) { throw new RuntimeException(
			 * "sso client memcache loading fail, memcacheFileDir is:" +
			 * clientFile.getAbsolutePath()); } } else { throw new
			 * RuntimeException(clientFile.getAbsolutePath() +
			 * " is not exist."); }
			 */
			// cache = new LocalCacheStorage();
			cache = SpringUtils.getBean("cacheProxy");// Redis缓存
		}
	}

	public static void addCache(String cacheKey, Object value) {
		if (cache == null) {
			initCache();
		}
		cache.add(cacheKey, value);
	}

	public static void addCache(String cacheKey, Object value, Integer timeOut) {
		if (cache == null) {
			initCache();
		}
		cache.add(cacheKey, value, timeOut);
	}

	public static void putCache(String cacheKey, Object value, Integer timeOut) {
		if (cache == null) {
			initCache();
		}
		cache.put(cacheKey, value, timeOut);
	}

	public static void putCache(String cacheKey, Object value) {
		if (cache == null) {
			initCache();
		}
		cache.put(cacheKey, value);
	}

	public static Object getCache(String cacheKey) {

		if (cache == null) {
			initCache();
		}
		return cache.get(cacheKey);
	}

	/** add **/
	public static void removeCache(String cacheKey) {

		if (cache == null) {
			initCache();
		}
		cache.remove(cacheKey);
	}

	public static CacheProxy getCache() {
		if (cache == null) {
			initCache();
		}
		return cache;
	}

}
