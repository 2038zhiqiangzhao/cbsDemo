package com.people2000.common.cache.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;

import com.people2000.common.cache.redis.HAConfig.ServerInfo;
import com.people2000.common.cache.redis.HAConfig.ServerProperties;

/**
 * 封装redis客户端
 * 
 * @author ningyu
 * @date 2012-10-20 下午1:43:53
 * @since
 * @version 1.1.1
 */
public class RedisClient implements InitializingBean, DisposableBean {

	/**
	 * 日志
	 */
	Log log = LogFactory.getLog(getClass());

	/**
	 * 节点唯一标识
	 */
	private String nodeUUID;

	/**
	 * redis client连接池
	 */
	private JedisPool pool;

	/**
	 * 配置参数
	 */
	private JedisPoolConfig poolConfig = null;

	/**
	 * 第一个服务IP
	 */
	private String host1 = "localhost";

	/**
	 * 第一个服务端口
	 */
	private int port1 = Protocol.DEFAULT_PORT;

	/**
	 * 第一个服务IP
	 */
	private String host2 = "localhost";

	/**
	 * 第一个服务端口
	 */
	private int port2 = Protocol.DEFAULT_PORT;

	/**
	 * 初始化client pool成功为true，在切换服务器的时候被赋值为false，切换成功后被赋值为true
	 */
	private AtomicBoolean poolInited = new AtomicBoolean(false);

	/**
	 * 集群信息 key
	 */
	public static final String CLUSTER_KEY = "DPAP-Redis-Cluster-Nodes";

	/**
	 * 初始化锁 key
	 */
	public static final String LOCK_KEY = "DPAP.redis.lock";

	private String configString;

	private HAConfig haConfig = new HAConfig();

	private Router router = null;

	@Override
	public void afterPropertiesSet() throws Exception {
		if (StringUtils.isNotBlank(configString)) {
			this.haConfig = parseConfig(configString.toString());
		} else {
			this.haConfig = parseConfig();
		}

		nodeUUID = UUID.randomUUID().toString();
		router = new Router(this.haConfig.groups, this.haConfig.failover);
	}

	public JedisPoolConfig getPoolConfig() {
		if (poolConfig == null) {
			poolConfig = new JedisPoolConfig();
			poolConfig.setMaxTotal(1024);
			poolConfig.setMaxIdle(200);
			poolConfig.setMaxWaitMillis(1000);
			poolConfig.setTestOnBorrow(false);
			poolConfig.setTestOnReturn(false);
		}
		return poolConfig;
	}

	public void setConfigString(String configString) {
		this.configString = configString;
	}

	public void setPoolConfig(JedisPoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}

	public void setHost1(String host1) {
		this.host1 = host1;
	}

	public void setPort1(int port1) {
		this.port1 = port1;
	}

	public boolean getPoolInited() {
		return poolInited.get();
	}

	public void setTimeout(int timeout) {
		this.haConfig.timeout = timeout;
	}

	public void setPassword(String password) {
		this.haConfig.password = password;
	}

	/**
	 * 保留方法，避免应用修改配置文件
	 * 
	 * @author ningyu
	 * @date 2013-9-4 下午5:21:06
	 * @param host2
	 * @see
	 */
	@Deprecated
	public void setHost2(String host2) {
		this.host2 = host2;
	}

	/**
	 * 保留方法，避免应用修改配置文件
	 * 
	 * @author ningyu
	 * @date 2013-9-4 下午5:21:28
	 * @param port2
	 * @see
	 */
	@Deprecated
	public void setPort2(int port2) {
		this.port2 = port2;
	}

	/**
	 * <p>
	 * 关闭连接
	 * </p>
	 * 
	 * @author ningyu
	 * @date 2012-10-24 下午1:47:57
	 * @param jedis
	 * @see
	 */
	private void disconnect(Jedis jedis) {
		jedis.disconnect();
	}

	public Jedis getResource() {
		return pool.getResource();
	}

	public void returnResource(Jedis jedis) {
		pool.returnResource(jedis);
	}

	public void returnBrokenResource(Jedis jedis) {
		pool.returnBrokenResource(jedis);
	}

	/**
	 * <p>
	 * 锁定，节点锁定服务器的锁定标记
	 * </p>
	 * 
	 * @author ningyu
	 * @date 2012-10-22 下午5:12:44
	 * @param j
	 * @return
	 * @see
	 */
	public boolean getLock(Jedis j1, Jedis j2) {
		boolean b1 = false, b2 = false;
		try {
			b1 = getLock(j1, LOCK_KEY);
		} catch (JedisDataException e) {
			b1 = true;
		}
		try {
			b2 = getLock(j2, LOCK_KEY);
		} catch (JedisDataException e) {
			b2 = true;
		}
		return b1 && b2;
	}

	/**
	 * <p>
	 * 锁定，节点锁定服务器的锁定标记
	 * </p>
	 * 
	 * @author ningyu
	 * @date 2012-10-22 下午5:13:13
	 * @param j
	 * @param lockKey
	 * @return
	 * @see
	 */
	public boolean getLock(Jedis j, String lockKey) {
		long result = 0;
		try {
			result = j.setnx(lockKey, nodeUUID);
			if (result == 1) {
				return true;
			} else if (nodeUUID.equals(j.get(lockKey))) {
				return true;
			}
		} catch (JedisConnectionException e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * 设置了主从redis关系之后释放锁操作方法
	 * 
	 * @author ningyu
	 * @date 2013-1-6 下午12:43:36
	 * @param master
	 * @see
	 */
	public void releaseLock(Jedis master) {
		releaseLock(master, LOCK_KEY);
	}

	/**
	 * j1,j2没有主从关系 释放锁操作方法
	 * 
	 * @author ningyu
	 * @date 2012-10-22 下午5:13:24
	 * @param j
	 * @see
	 */
	public void releaseLock(Jedis j1, Jedis j2) {
		releaseLock(j1, LOCK_KEY);
		releaseLock(j2, LOCK_KEY);
	}

	/**
	 * <p>
	 * 释放锁，戒掉释放对服务器的锁定标记
	 * </p>
	 * 
	 * @author ningyu
	 * @date 2012-10-22 下午5:13:58
	 * @param j
	 * @param lockKey
	 * @see
	 */
	public void releaseLock(Jedis j, String lockKey) {
		try {
			j.del(lockKey);
		} catch (JedisException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void destroy() {
		if (pool != null) {
			pool.destroy();
		}
	}

	public HAConfig parseConfig() {
		List<ServerProperties> servers = null;
		if (StringUtils.isNotBlank(host1) && port1 > 0) {
			servers = new ArrayList<ServerProperties>();
			ServerProperties sp1 = new ServerProperties();
			sp1.server = new ServerInfo();
			sp1.server.addr = this.host1;
			sp1.server.port = this.port1;
			sp1.timeout = haConfig.timeout;
			sp1.password = haConfig.password;
			servers.add(sp1);
		}
		if (StringUtils.isNotBlank(host2) && port2 > 0) {
			if (servers == null) {
				servers = new ArrayList<ServerProperties>();
			}
			ServerProperties sp2 = new ServerProperties();
			sp2.server = new ServerInfo();
			sp2.server.addr = this.host2;
			sp2.server.port = this.port2;
			sp2.timeout = haConfig.timeout;
			sp2.password = haConfig.password;
			servers.add(sp2);
		}
		if (servers != null) {
			haConfig.groups = servers;
		}
		return haConfig;
	}

	public HAConfig parseConfig(String configString) {
		// timeout
		Pattern p_timeout = Pattern.compile("timeout=([\\s\\S]+?);");
		Matcher m_timeout = p_timeout.matcher(configString);
		if (m_timeout.find()) {
			String s_timeout = m_timeout.group(1);
			log.info("timeout=" + s_timeout);
			try {
				haConfig.timeout = Integer.parseInt(s_timeout.trim());
			} catch (Exception ex) {
				log.error("timeout解析错误:", ex);
			}
		}

		// password
		Pattern p_password = Pattern.compile("password=([\\s\\S]+?);");
		Matcher m_password = p_password.matcher(configString);
		if (m_password.find()) {
			String s_password = m_password.group(1);
			log.info("password=" + s_password);
			try {
				haConfig.password = s_password.trim();
			} catch (Exception ex) {
				log.error("password解析错误:", ex);
			}
		}

		// servers
		Pattern p = Pattern.compile("servers=([\\s\\S]+?);",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(configString);
		if (m.find()) {
			String s_servers = m.group(1);
			log.info("servers=" + s_servers);
			String[] array = s_servers.trim().split(",");
			List<ServerProperties> servers = new ArrayList<ServerProperties>();
			for (String s : array) {
				ServerProperties sp = new ServerProperties();
				sp.server = new ServerInfo();
				String[] ss = s.split(":");
				if (ss.length >= 2) {
					sp.server.addr = ss[0];
					sp.server.port = Integer.parseInt(ss[1]);
					sp.timeout = haConfig.timeout;
					sp.password = haConfig.password;
				} else {
					log.error("配置错误:" + s);
				}
				servers.add(sp);
			}
			haConfig.groups = servers;
		} else {
			log.error("servers配置解析不到:" + configString);
		}
		// fail over开关
		Pattern p_failover = Pattern.compile("failover=([\\s\\S]+?);",
				Pattern.CASE_INSENSITIVE);
		Matcher m_failover = p_failover.matcher(configString);
		if (m_failover.find()) {
			try {
				String s_failover = m_failover.group(1);
				haConfig.failover = Boolean.parseBoolean(s_failover.trim());
			} catch (Throwable t) {
				log.error("failover开关解析出错", t);
			}
		}
		return haConfig;
	}

	public void onError() {
		if (!haConfig.failover) {
			return;
		}
		log.warn("onError:" + router.getServerProperties().generateKey());

		router.startRetry();
	}

	public class Router {

		private List<ServerProperties> all_props;// 保存最开始加载的配置,里面可能有失效的服务器。
		boolean failover;
		private AtomicBoolean isRetry = new AtomicBoolean(false);
		Retry retry = null;

		public Router(List<ServerProperties> props, boolean failover) {
			this.all_props = props;
			this.failover = failover;
			init();
		}

		// 触发重试逻辑
		public final void startRetry() {
			if (!isRetry.get()) {
				isRetry.set(true);
				retry = new Retry();
				executor_retry.execute(retry);
			}
		}

		ExecutorService executor_retry = Executors.newSingleThreadExecutor();

		volatile ServerProperties currentServer = null;

		final ServerProperties getServerProperties() {
			return currentServer;
		}

		/**
		 * 初始化
		 * 
		 * @author ningyu
		 * @date 2012-10-22 上午9:01:54
		 * @see
		 */
		final void init() {
			List<ServerProperties> props = all_props;
			if (props.size() <= 0) {
				log.error("server properties is null!");
				poolInited.set(false);
				return;
			}
			for (ServerProperties prop : props) {
				Jedis j = null;
				try {
					j = new Jedis(prop.server.addr, prop.server.port,
							prop.timeout);
					if (StringUtils.isNotBlank(prop.password)) {
						j.auth(prop.password);
					} else {
						j.ping();
					}
					j.slaveofNoOne();
					initPool(prop);
					poolInited.set(true);
					break;
				} catch (JedisConnectionException e) {
					poolInited.set(false);
					log.error(e.getMessage(), e);
				} finally {
					if (j != null) {
						disconnect(j);
					}
				}
			}
		}

		/**
		 * <p>
		 * 根据节点信息初始化client pool
		 * </p>
		 * 
		 * @author ningyu
		 * @date 2012-10-22 下午5:14:30
		 * @param master
		 * @see
		 */
		private final void initPool(ServerProperties prop) {
			if (pool != null) {
				pool.destroy();
			}
			currentServer = prop;
			pool = new JedisPool(getPoolConfig(), prop.server.addr,
					prop.server.port, prop.timeout, prop.password);
			log.info("Init local redundant pool.");
		}

		public void destroy() {
			if (retry != null) {
				retry.exit = true;
				synchronized (retry) {
					retry.notify();
				}
			}
			executor_retry.shutdownNow();
		}

		final class Retry implements Runnable {

			volatile boolean exit = false;

			@Override
			public void run() {

				while (!exit) {
					for (ServerProperties sp : all_props) {
						if (!sp.generateKey().equals(
								currentServer.generateKey())) {
							Jedis j = null;
							try {
								j = new Jedis(sp.server.addr, sp.server.port,
										sp.timeout);
								if (StringUtils.isNotBlank(sp.password)) {
									j.auth(sp.password);
								} else {
									j.ping();
								}
								j.slaveofNoOne();
								initPool(sp);
								exit = true;
								log.info("switch to "
										+ currentServer.generateKey());
								break;
							} catch (JedisConnectionException e) {
								log.error(e.getMessage(), e);
							} finally {
								if (j != null) {
									disconnect(j);
								}
							}
						}
					}
					if (!exit) {
						try {
							synchronized (Retry.this) {
								log.info("No server is available!Retry after 2 seconds");
								wait(1000 * 20);
							}
						} catch (InterruptedException ex) {
							log.warn("Retry Thread InterruptedException", ex);
						}
					} else {
						isRetry.set(false);
					}
				}

			}
		}
	}
}
