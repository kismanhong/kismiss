package com.softtech.kismiss.handler;

import java.util.Arrays;
import java.util.List;

import com.softtech.kismiss.model.Attribute;
import com.softtech.kismiss.model.PropertyInfo;

/**
 * @author Kisman Hong 
 * @email kismanhong@gmail.com
 * Errors Handler
 * 
 */
public class ErrorHandler {
	private static ErrorHandler instance = null;

	public static synchronized ErrorHandler getInstance() {
		if (instance == null) {
			instance = new ErrorHandler();
		}
		return instance;
	}

	/**
	 * @param list
	 * @param passAttribute
	 * @return
	 * checking attribute validation
	 * this should not happen when using annotation type
	 */
	public boolean isNotValidAttribute(List<Attribute> list, String passAttribute) {
		if (Arrays.binarySearch(getAttributes(list), passAttribute) >= 0)
			return false;
		return true;
	}

	
	public String errorAttributeNotExist(Object[] params, String code) {
		return ReadErrors.instance.getErrorMessage(code, params);
	}
	
	public String errorNotValid(String message, String code) {
		return ReadErrors.instance.getErrorMessage(code, message);
	}
	
	public String errorNotValid(String[] params, String code) {
		return ReadErrors.instance.getErrorMessage(code, params);
	}
	
	public String invalid(String attribute, String code) {
		return ReadErrors.instance.getErrorMessage(code, attribute);
	}

	/**
	 * @param infos
	 * @param compare
	 * @param clazz
	 * @return
	 * Attribute not exists detected
	 */
	@SuppressWarnings("rawtypes")
	public String errorNotExistString(List<PropertyInfo> infos,
			List<Attribute> compare, Class clazz) {
		String returns = "";
		for (PropertyInfo attribute : infos) {
			if (Arrays.binarySearch(getAttributes(compare), attribute
					.getAttributeName()) < 0)
				returns += " '" + attribute.getAttributeName() + "' ";
		}
		return ReadErrors.instance.getErrorMessage(ErrorCode.CANNOT_RESOLVED,
				returns, clazz.getName());
	}
	
	public String cannotResolvedDynamicField(String field, Class<?> clazz) {		
		return ReadErrors.instance.getErrorMessage(ErrorCode.CANNOT_RESOLVED_DYNAMIC_ATTRIBUTE,
				field, clazz.getName());
	}
	
	public String inconsistenceHierarchy(String field, int rows, int height) {		
		return ReadErrors.instance.getErrorMessage(ErrorCode.INCONSISTENCE_HIERARCHY,
				field, "define row : "+rows +",but height :"+height);
	}
	
	public String inconsistenceCalculation(String field, int cal, int type) {		
		return ReadErrors.instance.getErrorMessage(ErrorCode.INCONSISTENCE_CALCULATION,
				field, "define calculation : "+cal +",but calculation type :"+type);
	}

	/**
	 * @return
	 */
	public String reportCallTwice() {
		return ReadErrors.instance.getErrorMessage(ErrorCode.REPORT_CALL_TWICE);
	}

	/**
	 * @param total
	 * @param actually
	 * @return
	 * maximum width reached
	 */
	public String maxColumnWidthReached(int total, int actually) {
		return ReadErrors.instance.getErrorMessage(
				ErrorCode.MAX_COLUMN_WIDTH_REACHED, String.valueOf(total),
				String.valueOf(actually));
	}
	
	/**
	 * @param total
	 * @param actually
	 * @return
	 */
	public String maxPaperWidthReached(int total, int actually) {
		return ReadErrors.instance.getErrorMessage(
				ErrorCode.MAX_PAPER_WIDTH_REACHED, String.valueOf(total),
				String.valueOf(actually));
	}
	
	/**
	 * @param total
	 * @param actually
	 * @return
	 */
	public String maxPaperHeightReached(int total, int actually) {
		return ReadErrors.instance.getErrorMessage(
				ErrorCode.MAX_PAPER_HEIGHT_REACHED, String.valueOf(total),
				String.valueOf(actually));
	}
	
	/**
	 * @param atts
	 * @return Array of attribute name
	 */
	private static String[] getAttributes(List<Attribute> atts) {
		String[] returns = new String[atts.size()];
		for (int index = 0; index < atts.size(); index++) {
			returns[index] = atts.get(index).getName();
		}
		Arrays.sort(returns);
		return returns;
	}
	
	/**
	 * @param clazz
	 * @return
	 */
	public String instanceGroupInvalid(Class<?> clazz) {
		return ReadErrors.instance.getErrorMessage(
				ErrorCode.INVALID_GROUP_INSTANCE, clazz.getCanonicalName(),
				"");
	}
	
	/**
	 * @param clazz
	 * @return
	 */
	public String mustBeDeclared(Class<?> clazz, String errorCode) {
		return ReadErrors.instance.getErrorMessage(
				errorCode, clazz.getCanonicalName(),
				"");
	}
	public String invalidPosition(int position, int realPosition) {
		return ReadErrors.instance.getErrorMessage(
				ErrorCode.INVALID_POSITION,""+position,
				""+realPosition);
	}
	
	/**
	 * @param clazz
	 * @return
	 */
	public String mustBeDeclared(Class<?> clazz) {
		return ReadErrors.instance.getErrorMessage(
				ErrorCode.HEADER_MUST_BE_DECLARED, clazz.getCanonicalName(),
				"");
	}
	
}
