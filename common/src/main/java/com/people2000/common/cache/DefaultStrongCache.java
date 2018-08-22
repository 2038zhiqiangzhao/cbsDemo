package com.people2000.common.cache;

import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.people2000.common.cache.provider.IBatchCacheProvider;
import com.people2000.common.cache.storage.StrongCacheStorage;

/**
 * 
 * 
 *******************************************
 * <b style="font-family:微软雅黑"><small>Description：只读缓存默认实现</small></b> </br> <b
 * style="font-family:微软雅黑"><small>HISTORY</small></b></br> <b
 * style="font-family:微软雅黑"><small> ID DATE PERSON REASON</small></b><br>
 ********************************************
 * <div style="font-family:微软雅黑,font-size:70%"> 1 2011-4-14 樊斌 新增 </div>
 ********************************************
 */
@Deprecated
public abstract class DefaultStrongCache<K, V> implements
		RefreshableCache<K, V>, InitializingBean, DisposableBean {

	private static final Log LOG = LogFactory.getLog(DefaultStrongCache.class);

	private IBatchCacheProvider<K, V> cacheProvider;

	private StrongCacheStorage<K, V> cacheStorage;

	/**
	 * 自动刷新间隔时间,单位秒,默认15分钟
	 */
	private long interval = 15L * 60 * 1000;

	/**
	 * 自动刷新线程
	 */
	private ReloadThread thread;

	/**
	 * 数据提供者provider提供的数据最后修改时间,作为刷新缓存的时间戳
	 */
	private Date modifyTime;

	public DefaultStrongCache() {
		this.cacheStorage = new StrongCacheStorage<K, V>();
	}

	public void setInterval(int seconds) {
		this.interval = (long) seconds * 1000;
	}

	@SuppressWarnings("unchecked")
	public void afterPropertiesSet() throws Exception {
		// 注册cache
		CacheManager.getInstance().registerCacheProvider(this);
		Map<K, V> map = this.cacheProvider.get();
		cacheStorage.set(map);
		modifyTime = this.cacheProvider.getLastModifyTime();
		// 启动刷新线程
		thread = new ReloadThread("STRONG_CACHE_" + this.getUUID());
		thread.setDaemon(true);
		thread.start();
	}

	public void setCacheProvider(IBatchCacheProvider<K, V> cacheProvider) {
		this.cacheProvider = cacheProvider;
	}

	@Override
	public final V get(K key) {
		return cacheStorage.get(key);
	}

	@Override
	public final Map<K, V> get() {
		return cacheStorage.get();
	}

	/**
	 * 应用停止时终止线程
	 * 
	 * @see org.springframework.beans.factory.DisposableBean#destroy() destroy
	 * @throws Exception
	 * @since:
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

	/**
	 * 
	 * 自动刷新缓存
	 * <p style="display:none">
	 * modifyRecord
	 * </p>
	 * <p style="display:none">
	 * version:V1.0,author:ningyu,date:2013-3-28 上午10:37:20,content:TODO
	 * </p>
	 * 
	 * @author ningyu
	 * @date 2013-3-28 上午10:37:20
	 * @since
	 * @version
	 */
	private class ReloadThread extends Thread {
		private volatile int state;

		ReloadThread(String threadName) {
			super(threadName);
		}

		@Override
		public void run() {
			while (true) {
				try {
					state = 2;
					// 刷新间隔
					Thread.sleep(interval);
				} catch (InterruptedException e) {
					LOG.error(e.getMessage());
					break;
				}
				try {
					state = 1;
					// 如果最后更新时间早于当前时间
					// 更新所有数据
					refresh();
				} catch (Exception ex) {
					LOG.error(ex.getMessage());
					break;
				}
			}
		}

	}

	/**
	 * 
	 * <p>
	 * 刷新策略
	 * </p>
	 * 
	 * @author ningyu
	 * @date 2013-3-28 上午10:38:41
	 * @return
	 * @see com.deppon.foss.framework.cache.RefreshableCache#refresh()
	 */
	public boolean refresh() {
		Date lastTime = cacheProvider.getLastModifyTime();
		if (modifyTime != null && lastTime != null
				&& lastTime.after(modifyTime)) {
			Map<K, V> map = cacheProvider.get();
			cacheStorage.set(map);
			modifyTime = lastTime;
			return true;
		}
		return false;
	}

	@Override
	public boolean refresh(K... keys) {
		throw new RuntimeException("Strong Cache Cannot Refresh Part!");
	}

	public void invalid() {
		cacheStorage.clear();
		// 因为是批量加载所以全部失效考虑重新加载的问题
		Map<K, V> map = this.cacheProvider.get();
		cacheStorage.set(map);
		modifyTime = this.cacheProvider.getLastModifyTime();
	}

	public void invalid(K key) {
		// cacheStorage.remove(key);
		throw new RuntimeException("Strong Cache Cannot Invalid Part!");
	}

	public void invalidMulti(K... keys) {
		throw new RuntimeException("Strong Cache Cannot Invalid Part!");
	}

	// public Boolean exists(K key) {
	// return cacheStorage.exists(key);
	// }
}
