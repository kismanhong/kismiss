package com.softtech.kismiss.main;


public class BaseKismissReport extends KismissReportProcessing{
	
	private static KismissReport instance = null;

	public static synchronized KismissReport initForClass(Class<?> clazz) {
		if (instance == null) {
			instance = new KismissReport();
		}
		
		return instance;
	}
}
