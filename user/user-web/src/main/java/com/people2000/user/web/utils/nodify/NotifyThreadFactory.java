package com.people2000.user.web.utils.nodify;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NotifyThreadFactory implements ThreadFactory {

	private static final AtomicInteger sequence = new AtomicInteger();

	private final String NamePrefix;

	private NotifyThreadFactory(String namePrefix) {
		this.NamePrefix = namePrefix;
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		t.setName(NamePrefix + sequence.incrementAndGet());
		return t;
	}

	public static NotifyThreadFactory newInstance() {
		return new NotifyThreadFactory("login-notify-handler-");
	}

}
