package com.xl.memcache.server;

import java.util.HashMap;

public class CacheManager<K, V> {

	public static CacheManager cacheManager = new CacheManager();

	public HashMap<K, V> map = new HashMap<>();
	public int max_size;

	private CacheManager(){ }

	public CacheManager getInstance(){
		return cacheManager;
	}

	public void init(int max_size){
		this.max_size = max_size;
	}

	public HashMap<K, V> getCache(){
		return map;
	}

}
