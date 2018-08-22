package com.people2000.common.cache;

import java.util.Date;

public abstract interface CacheProxy {
	public abstract boolean put(String paramString, Object paramObject);

	public abstract boolean put(String paramString, Object paramObject,
			int paramInt);

	public abstract boolean put(String paramString, Object paramObject,
			Date paramDate);

	public abstract Object get(String paramString);

	public abstract boolean remove(String paramString);

	// public abstract boolean putString(String paramString1, String
	// paramString2);
	//
	// public abstract boolean putString(String paramString1, String
	// paramString2, int paramInt);
	//
	// public abstract boolean putString(String paramString, Object paramObject,
	// Date paramDate);
	//
	// public abstract String getString(String paramString);
	//
	// public abstract String getString(String paramString1, String
	// paramString2);

	public abstract boolean add(String paramString, Object paramObject);

	public abstract boolean add(String paramString, Object paramObject,
			int paramInt);

	// public abstract boolean add(String paramString, Object paramObject, Date
	// paramDate);
	//
	// public abstract boolean replace(String paramString, Object paramObject);
	//
	// public abstract boolean replace(String paramString, Object paramObject,
	// int paramInt);
	//
	// public abstract boolean replace(String paramString, Object paramObject,
	// Date paramDate);

}