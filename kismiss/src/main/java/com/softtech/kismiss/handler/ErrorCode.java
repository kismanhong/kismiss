package com.softtech.kismiss.handler;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * simplify report generation
 * ErrorCode for defining the error, these codes use for trace purpose
 * this is used for java 1.5 above
 * 
 */
public interface ErrorCode {
	
	static final String CANNOT_RESOLVED           			= "00000001";	//cannot resolve given information
	static final String CANNOT_RESOLVED_ATTRIBUTE 			= "00000002";	//attribute given not defined in POJO, this should happen when use annotation
	static final String INVALID_DEFINED_CALCUTION 			= "00000003";	//calculation defined out of format
	static final String REPORT_CALL_TWICE 		 			= "00000004";	//report call twice
	static final String MAX_COLUMN_WIDTH_REACHED 			= "00000005";   //total of column/s width greater than report size
	static final String MAX_PAPER_WIDTH_REACHED 			= "00000006";   //paper width smaller than defined
	static final String MAX_PAPER_HEIGHT_REACHED 			= "00000007";   //paper height smaller than defined
	static final String CANNOT_RESOLVED_DYNAMIC_ATTRIBUTE 	= "00000009";
	static final String INVALID_POSITION 					= "00000010";
	
	static final String INVALID_JAVA_TYPE 					= "00000011";
	static final String INVALID_INNER_PROPERTY 				= "00000012";
	static final String INVALID_INNER_PROPERTY_WIDTH 		= "00000013";
	static final String INVALID_NUMBER_OF_POSITION	 		= "00000014";
	static final String INVALID_CALCULATION_PROPERTY 		= "00000015";
	static final String INVALID_INNER_PROPERTY_FIELD 		= "00000016";
	
	static final String CANNOT_RESOLVED_GROUP_BY 			= "00000020";	//attribute groupBy cannot be resolved
	static final String CANNOT_RESOLVED_GROUP_RECORD		= "00000021";	
	
	static final String INVALID_GROUP_INSTANCE 		    	= "00000040";
	
	static final String KISMISS_MUST_BE_DECLARED        	= "00000060";
	static final String HEADER_MUST_BE_DECLARED          	= "00000061";
	static final String DETAIL_MUST_BE_DECLARED          	= "00000062";
	
	static final String INCONSISTENCE_HIERARCHY 			= "00000080";
	static final String INCONSISTENCE_CALCULATION			= "00000081";

	
}
