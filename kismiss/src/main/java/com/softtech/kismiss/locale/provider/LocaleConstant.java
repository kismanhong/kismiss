package com.softtech.kismiss.locale.provider;

public class LocaleConstant {
	public static LocaleProvider localeProvider = null;
	
	public static LocaleProvider getLocaleProvider(){
		return localeProvider;
	}
	
	@SuppressWarnings("static-access")
	public void setLocaleProvider(LocaleProvider localeProvider){
		this.localeProvider = localeProvider;
	}
}
