package com.softtech.kismiss.constant;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * simplify report generation
 * XlsReportParam is used for handling the parameter that send to jasper report
 * this is used for java 1.5 above
 */
public interface XlsReportParam {
	
	public static final String IS_ONE_PAGE_PER_SHEET = "IS_ONE_PAGE_PER_SHEET";
	
	public static final String IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS = "IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS";
	
	public static final String IS_WHITE_PAGE_BACKGROUND = "IS_WHITE_PAGE_BACKGROUND";
	
	public static final String IS_AUTO_DETECT_CELL_TYPE = "IS_CREATING_BATCH_MODE_BOOKMARKS";
	
	public static final String IS_DETECT_CELL_TYPE = "IS_DETECT_CELL_TYPE";
	
	public static final String SHEET_NAMES = "SHEET_NAMES";
	
	public static final String IS_FONT_SIZE_FIX_ENABLED = "IS_FONT_SIZE_FIX_ENABLED";
	
}
