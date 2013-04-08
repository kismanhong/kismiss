package com.softtech.kismiss.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.softtech.kismiss.exception.ReportDataAccessException;
import com.softtech.kismiss.handler.ErrorCode;
import com.softtech.kismiss.handler.ErrorHandler;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * Getting information from methods
 * do a few manipulation using this method utils
 */
public class MethodUtils {
	private static final String GET = "get";
	
	private static final String CLAZZ = "class ";
	
	private static final String[] javaType = {"java.lang.Double", "java.lang.Integer", "java.lang.Long", "java.lang.Float", 
			"class java.lang.String", "class java.util.Date", "class java.lang.Integer", "double", "int", "float", "number"  };
	
	
//	private static final String CLASS = "Class";

//	public static boolean isGetter(Method method) {
//		if (method.getName().startsWith(GET)
//				&& !method.getName().endsWith(CLASS))
//			return true;
//		return false;
//	}
	/**
	 * @param method
	 * @return
	 * checking whether a method is getter or not
	 */
	public static boolean isGetter(Method method) {
		if (!method.getName().startsWith(GET))
			return false;
		if (method.getParameterTypes().length != 0)
			return false;
		if (void.class.equals(method.getReturnType()))
			return false;
		return true;
	}
	
	/**
	 * @param name
	 * @return
	 * get method name without gate
	 */
	public static String getFieldName(String name) {
		return StringUtils.uncapitalize(StringUtils.removeStart(name, GET));
	}
	
	/**
	 * @param type
	 * @return
	 * getting return type
	 * @throws Exception 
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 */
	public static String getReturnType(String propertyName, Class<?> returnType) throws ReportDataAccessException {
		String type = returnType.toString();
		try {
			if ("class java.lang.String".equals(type) || returnType.isEnum())
				return "java.lang.String";	
			else if ("int".equals(type) || "class java.lang.Integer".equals(type))
				return "java.lang.Integer";
			else if ("long".equals(type) || "class java.lang.Long".equals(type) )
				return "java.lang.Long";
			else if ("float".equals(type) || "class java.lang.Float".equals(type))
				return "java.lang.Float";
			else if ("double".equals(type) || "class java.lang.Double".equals(type))
				return "java.lang.Double";
			else if ("class java.util.Date".equals(type))
				return "java.util.Date";
			else if ("boolean".equals(type) || "class java.lang.Boolean".equals(type))
				return "java.lang.Boolean";
			else if ("class java.lang.Byte".equals(type))
				return "java.lang.Byte";
			else if ("class java.sql.Timestamp".equals(type))
				return "class java.sql.Timestamp";
			else if ("class java.lang.Short".equals(type))
				return "java.lang.Short";
			else if ("class java.math.BigDecimal".equals(type))
				return "java.math.BigDecimal";
			else if ("class java.lang.Number".equals(type))
				return "java.lang.Number";
			else if ("class java.sql.Time".equals(type))
				return "java.sql.Time";
			else 
				return innerClassPropertyType(propertyName, type);
		} catch (ReportDataAccessException e) {
			throw new ReportDataAccessException(e);
		}
//		return type;
	}
	
	/**
	 * @param propertyName
	 * @param type
	 * @return
	 * @throws Exception
	 * method for getting last field of the declared report
	 */
	private static String innerClassPropertyType(String propertyName, String type) throws ReportDataAccessException
	{
		String innerPropertyName=null;
		Field field= null;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(StringUtils.remove(type, CLAZZ));
			String[] splittedPropertyName = StringUtils.split(propertyName,".");
			
			if(splittedPropertyName.length > 1){
				innerPropertyName = splittedPropertyName[1];
				field = clazz.getDeclaredField(innerPropertyName);
				for(int i =0; i < splittedPropertyName.length - 1; i++){
					innerPropertyName = splittedPropertyName[i+1];
					field = clazz.getDeclaredField(splittedPropertyName[i+1]);
					if(!ArrayUtils.contains(javaType, field.getType().toString()))
						clazz = Class.forName(StringUtils.remove(field.getType().toString(), CLAZZ));				
				}
			}
			
		} catch (Exception e) {	
			Object[] params = new Object[2];
			params[0] = innerPropertyName;
			params[1] = clazz.getCanonicalName();
				throw new ReportDataAccessException(ErrorHandler.getInstance()
						.errorAttributeNotExist(params, ErrorCode.INVALID_INNER_PROPERTY_FIELD));
		}
		
//		if(!ArrayUtils.contains(javaType, field.getType().toString())){ // the last field is not accepted by jasper report
//			throw new KismissException(ErrorHandler.getInstance().errorNotValid(field.getType().toString(), ErrorCode.INVALID_JAVA_TYPE));
//		}
		return getReturnType(innerPropertyName, field.getType());
	}
	
	
	
}
