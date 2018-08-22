package com.people2000.common.cache.provider;

import java.util.Map;

import com.people2000.common.cache.ICacheProvider;

/**
 * 
 * 
 *******************************************
 * <b style="font-family:微软雅黑"><small>Description:批量加载缓存接口</small></b> </br> <b
 * style="font-family:微软雅黑"><small>HISTORY</small></b></br> <b
 * style="font-family:微软雅黑"><small> ID DATE PERSON REASON</small></b><br>
 ********************************************
 * <div style="font-family:微软雅黑,font-size:70%"> 1 2011-4-14 樊斌 新增 </div>
 ********************************************
 */
public interface IBatchCacheProvider<K, V> extends ICacheProvider<K, V> {

	/**
	 * 批量加载数据 get
	 * 
	 * @return
	 * @return Map<K,V>
	 * @since: 0.6
	 */
	Map<K, V> get();
}
