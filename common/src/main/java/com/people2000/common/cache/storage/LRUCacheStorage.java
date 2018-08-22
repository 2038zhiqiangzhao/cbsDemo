package com.people2000.common.cache.storage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.people2000.common.cache.ICacheStorage;

/**
 * 
 * 
 *******************************************
 * <b style="font-family:微软雅黑"><small>Description:LRU缓存存储</small></b> </br> <b
 * style="font-family:微软雅黑"><small>HISTORY</small></b></br> <b
 * style="font-family:微软雅黑"><small> ID DATE PERSON REASON</small></b><br>
 ********************************************
 * <div style="font-family:微软雅黑,font-size:70%"> 1 2011-4-14 樊斌 新增 </div>
 ********************************************
 */
@Deprecated
public class LRUCacheStorage<K, V> implements ICacheStorage<K, V> {

	private volatile Map<K, V> map;

	/**
	 * 日志
	 */
	Log log = LogFactory.getLog(getClass());

	public LRUCacheStorage() {
		this.map = new ConcurrentHashMap<K, V>();
	}

	/**
	 * 
	 * @see com.deppon.foss.framework.cache.ICacheStorage#set(java.lang.Object,
	 *      java.lang.Object) set
	 * @param key
	 * @param value
	 * @since:0.6
	 */
	@Override
	public void set(K key, V value) {
		map.put(key, value);
	}

	/**
	 * 
	 * @see com.deppon.foss.framework.cache.ICacheStorage#get(java.lang.Object)
	 *      get
	 * @param key
	 * @return
	 * @since:0.6
	 * 
	 *            实现序列化范序列化 来进行克隆
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V get(K key) {
		return map.get(key);
		// V v = map.get(key);
		// if(v != null) {
		// String re = CacheUtils.toJsonString(v);
		// if (re != null) {
		// V result = null;
		// try {
		// result = (V) JSON.parse(re);
		// } catch(Exception e) {
		// LOG.error(e);
		// }
		// return result;
		// }
		// }
		// return null;
	}

	/**
	 * 
	 * @see com.deppon.foss.framework.cache.ICacheStorage#remove(java.lang.Object)
	 *      remove
	 * @param key
	 * @since:0.6
	 */
	@Override
	public void remove(K key) {
		map.remove(key);
	}

	/**
	 * 
	 * @see com.deppon.foss.framework.cache.ICacheStorage#clear() clear
	 * @since:0.6
	 */
	@Override
	public void clear() {
		map.clear();
	}

	/**
	 * 
	 * @see com.deppon.foss.framework.cache.ICacheStorage#get() get
	 * @return
	 * @since:0.6
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<K, V> get() {
		return map;
		// Map<K, V> value = null;
		// if(map != null) {
		// value = new ConcurrentHashMap<K, V>();
		// for(Map.Entry<K, V> entry : map.entrySet()) {
		// V v = entry.getValue();
		// String re = CacheUtils.toJsonString(v);
		// if (re != null) {
		// V result = null;
		// try {
		// result = (V) JSON.parse(re);
		// value.put(entry.getKey(), result);
		// } catch(Exception e) {
		// LOG.error(e);
		// }
		// }
		// }
		// }
		// return value;
	}

	/**
	 * 
	 * putAll
	 * 
	 * @param m
	 * @return void
	 * @since:0.6
	 */
	public void putAll(Map<K, V> m) {
		this.map.putAll(m);
	}

	/**
	 * 此方法暂无作用
	 * 
	 * @see com.deppon.foss.framework.cache.ICacheStorage#set(java.util.Map) set
	 * @param values
	 * @since:0.6
	 */
	@Override
	public void set(Map<K, V> values) {
		this.map.putAll(values);
	}

	@Override
	public Boolean exists(K key) {
		return this.map.containsKey(key);
	}

}
