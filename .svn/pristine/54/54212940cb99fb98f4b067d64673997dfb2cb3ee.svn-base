package com.people2000.user.web.utils.nodify;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 简易版登录信息处理，后续切到MQ
 * 
 * @author lws
 */
public class LoginNotifyUtil {

	private final int QueueSize = 10000;

	private final int CoreSize;
	private final int MaxSize;

	private final RejectedExecutionHandler rejectHandler = new ThreadPoolExecutor.CallerRunsPolicy();

	private ThreadPoolExecutor executor;

	private int getMaxThreadCount() {
		return Math.min(4, Runtime.getRuntime().availableProcessors());
	}

	private LoginNotifyUtil() {
		CoreSize = 2;
		MaxSize = Math.max(CoreSize + 1, getMaxThreadCount());
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(
				QueueSize);
		executor = new ThreadPoolExecutor(CoreSize, MaxSize, 5,
				TimeUnit.MINUTES, queue, NotifyThreadFactory.newInstance(),
				rejectHandler);
	}

	public Future<?> submit(Runnable run) {
		return executor.submit(run);
	}

	private static volatile LoginNotifyUtil instance;

	public static LoginNotifyUtil getInstance() {
		if (instance == null) {
			synchronized (LoginNotifyUtil.class) {
				if (instance == null) {
					instance = new LoginNotifyUtil();
				}
			}
		}
		return instance;
	}

}
