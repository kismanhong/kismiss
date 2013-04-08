package com.softtech.kismiss.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * simplify report generation
 * KismissReport is used for accessing the AbstractKismissReport
 * this is used for java 1.5 above
 */
public class KismissReport extends KismissReportInstance{
	
	private final static Logger logger = LoggerFactory.getLogger(KismissReport.class);
	
	private static KismissReport instance = null;

	public static synchronized KismissReport getInstance() {
		if (instance == null) {
			instance = new KismissReport();
		}
		return instance;
	}
	
	public KismissReport() {
		logger.info("[Kismiss] Begin Initialization Report Information!");
	}
}
