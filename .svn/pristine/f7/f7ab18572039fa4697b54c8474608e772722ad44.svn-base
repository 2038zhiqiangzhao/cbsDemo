package com.people2000.common.cache;

/**
 * 允许刷新缓存接口
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:ningyu,date:2012-10-25 下午1:34:59,content:TODO
 * </p>
 * 
 * @author ningyu
 * @date 2012-10-25 下午1:34:59
 * @since
 * @version
 */
public interface RefreshableCache<K, V> extends ICache<K, V> {
	/**
	 * 刷新缓存
	 * 
	 * 根据provider提供的最后修改时间去刷新这段时间之内修改的数据 如果是LRU的根据最后修改时间刷新时间段的数据
	 * 如果是Strong根据最后修改时间刷新所有数据
	 * 
	 * @author ningyu
	 * @date 2012-11-12 上午9:51:16
	 * @return
	 * @see
	 */
	boolean refresh();

	/**
	 * 刷新Key对应的缓存
	 * 
	 * 根据provider提供的最后修改时间去刷新这段时间之内修改的数据 如果是LRU的根据传入的Key修改缓存数据 如果是Strong的会Throws
	 * RuntimeException异常，不允许刷新部分数据
	 * 
	 * @author ningyu
	 * @date 2012-11-12 上午9:51:18
	 * @param keys
	 * @return
	 * @see
	 */
	boolean refresh(K... keys);
}
