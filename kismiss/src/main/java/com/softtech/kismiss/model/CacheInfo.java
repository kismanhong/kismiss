package com.softtech.kismiss.model;

import com.softtech.kismiss.enumer.CacheStrategyType;

/**
 * @author kismanhong
 *
 */
public class CacheInfo {
	private String name;
	private CacheStrategyType cacheStrategyType;
	private String relativePath;
	
	public CacheInfo(String name, CacheStrategyType cacheStrategyType,
			String relativePath) {
		super();
		this.name = name;
		this.cacheStrategyType = cacheStrategyType;
		this.relativePath = relativePath;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CacheStrategyType getCacheStrategyType() {
		return cacheStrategyType;
	}
	public void setCacheStrategyType(CacheStrategyType cacheStrategyType) {
		this.cacheStrategyType = cacheStrategyType;
	}
	public String getRelativePath() {
		return relativePath;
	}
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
	
}
