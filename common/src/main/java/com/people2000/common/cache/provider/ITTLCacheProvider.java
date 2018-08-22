package com.people2000.common.cache.provider;

/**
 * TTL的缓存数据提供者
 * 
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:ningyu,date:2013-1-31 上午10:54:53,content:TODO
 * </p>
 * 
 * @author ningyu
 * @date 2013-1-31 上午10:54:53
 * @since
 * @version
 */
public interface ITTLCacheProvider<V> {

	/**
	 * 加载单个元素 get
	 * 
	 * @param key
	 * @return
	 * @return V
	 * @since:
	 */
	V get(String key);

}
