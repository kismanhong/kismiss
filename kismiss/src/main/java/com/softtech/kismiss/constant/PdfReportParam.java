package com.softtech.kismiss.constant;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * simplify report generation
 * PdfReportParam is used for handling the parameter that send to jasper report
 * this is used for java 1.5 above
 */
public interface PdfReportParam {
	
	public static final String OWNER_PASSWORD = "OWNER_PASSWORD";
	
	public static final String USER_PASSWORD = "USER_PASSWORD";
	
	public static final String IS_ENCRYPTED = "IS_ENCRYPTED";
	
	public static final String IS_CREATING_BATCH_MODE_BOOKMARKS = "IS_CREATING_BATCH_MODE_BOOKMARKS";
	
	public static final String IS_COMPRESSED = "IS_COMPRESSED";
	
	public static final String IS_128_BIT_KEY = "IS_128_BIT_KEY";
	
	public static final String PERMISSIONS = "PERMISSIONS";
	
	public static final String PDF_VERSION = "PDF_VERSION";
	
	public static final String METADATA_TITLE = "METADATA_TITLE";
	
	public static final String METADATA_AUTHOR = "METADATA_AUTHOR";
	
	public static final String METADATA_SUBJECT = "METADATA_SUBJECT";
	
	public static final String METADATA_KEYWORDS = "METADATA_KEYWORDS";
	
	public static final String METADATA_CREATOR = "METADATA_CREATOR";
	
	public static final String FORCE_LINEBREAK_POLICY = "FORCE_LINEBREAK_POLICY";
	
	public static final String FORCE_SVG_SHAPES = "FORCE_SVG_SHAPES";
	
	public static final String HYPERLINK_PRODUCER_FACTORY = "HYPERLINK_PRODUCER_FACTORY";
	
	public static final String PDF_JAVASCRIPT = "IS_ENCRYPTED";
	
}
