package com.softtech.kismiss.utils;

import java.util.List;

import com.softtech.kismiss.exception.ReportDataAccessException;
import com.softtech.kismiss.handler.ErrorCode;
import com.softtech.kismiss.handler.ErrorHandler;
import com.softtech.kismiss.model.PropertyInfo;


/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 *
 */
public class ReportUtil {
	private static ReportUtil instance = null;

	public static synchronized ReportUtil getInstance() {
		if (instance == null) {
			instance = new ReportUtil();
		}
		return instance;
	}
	
	public Object[] getValues(List<PropertyInfo> infos, String attribute, String groupBy, Class<?> clazz) throws ReportDataAccessException
	{
		Object[] returns = new Object[5];
			boolean notExist = true;
			boolean validAttribute = false;
				for (PropertyInfo att : infos) {
					//checking attribute calculation is valid or not
					if (att.getAttributeName().equals(attribute)) {
						returns[0] = att.getX();
						returns[1] = att.getY();
						returns[2] = att.getWidth();
						returns[3] = att.getHeight();
						returns[4] = att.getType();
						notExist = false;
						//break;
					}
					//checking groupBy attribute is valid or not
					if(att.getAttributeName().equals(groupBy))
						validAttribute = true;
				}
			if(notExist){
				Object[] params = new Object[2];
				params[0] = attribute;
				params[1] = clazz.getCanonicalName();
					throw new ReportDataAccessException(ErrorHandler.getInstance()
							.errorAttributeNotExist(params, ErrorCode.CANNOT_RESOLVED_ATTRIBUTE));
			}
			
			if(!validAttribute )
			{
				Object[] params = new Object[2];
				params[0] = groupBy;
				params[1] = clazz.getCanonicalName();
					throw new ReportDataAccessException(ErrorHandler.getInstance().errorAttributeNotExist(params, ErrorCode.CANNOT_RESOLVED_GROUP_BY));
			}
		return returns;
	}
	
	
	public int[] findPropertyOfMatchAttribute(List<PropertyInfo> infos, String attribute, Class<?> clazz) throws ReportDataAccessException
	{
		int[] returns = new int[4];
			boolean notExist = true;
				for (PropertyInfo att : infos) {
					//checking attribute calculation is valid or not
					if (att.getAttributeName().equals(attribute)) {
						returns[0] = att.getX();
						returns[1] = att.getY();
						returns[2] = att.getWidth();
						returns[3] = att.getHeight();
						notExist = false;
						//break;
					}
				}
			if(notExist){
				Object[] params = new Object[2];
				params[0] = attribute;
				params[1] = clazz.getCanonicalName();
					throw new ReportDataAccessException(ErrorHandler.getInstance().errorAttributeNotExist(params, ErrorCode.CANNOT_RESOLVED_ATTRIBUTE));
			}	
		return returns;
	}
	
	public Object[] findMatchAttribute(List<PropertyInfo> infos, String attribute, Class<?> clazz) throws ReportDataAccessException
	{
		Object[] returns = new Object[5];
			boolean notExist = true;
				for (PropertyInfo att : infos) {
					//checking attribute calculation is valid or not
					if (att.getAttributeName().equals(attribute)) {
						returns[0] = att.getX();
						returns[1] = att.getY();
						returns[2] = att.getWidth();
						returns[3] = att.getHeight();
						returns[4] = att.getType();
						notExist = false;
						break;
					}
				}
			if(notExist){
				Object[] params = new Object[2];
				params[0] = attribute;
				params[1] = clazz.getCanonicalName();
					throw new ReportDataAccessException(ErrorHandler.getInstance().errorAttributeNotExist(params, ErrorCode.CANNOT_RESOLVED_ATTRIBUTE));
			}		
		return returns;
	}
}
