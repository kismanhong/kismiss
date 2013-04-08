package com.softtech.kismiss.locale.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.DefaultTextProvider;
import com.opensymphony.xwork2.TextProvider;

public class Struts2LocaleProvider implements LocaleProvider{
	private static final TextProvider TEXT_PROVIDER = new DefaultTextProvider();
	private final static Logger logger = LoggerFactory.getLogger(Struts2LocaleProvider.class);
	
	public String getMessage(String key){
		try {
			return TEXT_PROVIDER.getText(key);
		} catch (Exception e) {
			logger.warn("Cannot Resolve Text : {}", key);
			return key;
		}
	}
}
