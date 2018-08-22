package com.people2000.common.cache.storage;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import com.people2000.common.cache.CacheProxy;

public interface LocalCache extends CacheProxy {
	public boolean put(String key, Object value);

	public boolean put(String key, Object value, Date expiry);

	public boolean put(String key, Object value, int TTL);

	public Object get(String key);

	public boolean remove(String key);

	public boolean clear();

	public int size();

	public Set<String> keySet();

	public Collection<Object> values();

	public boolean containsKey(String key);

	public void destroy();
}
