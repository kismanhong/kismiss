package com.softtech.kismiss.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.softtech.kismiss.exception.KismissException;
import com.softtech.kismiss.main.Person;
import com.softtech.kismiss.property.DynamicField;
import com.softtech.kismiss.property.FieldGroup;

/**
 * @author Kisman Hong
 * handler the dynamic group and dynamic field choice
 */
public class FieldUtils {
	
	private static FieldUtils instance = null;

	public static synchronized FieldUtils getInstance() {
		if (instance == null) {
			instance = new FieldUtils();
		}
		return instance;
	}
	
	/**
	 * @param clazz
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws KismissException 
	 */
	private Map<String, String> getGroupProperty(Class<?> clazz)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, KismissException {
		Map<String, String> result = new HashMap<String, String>();
		Field[] fields = clazz.getDeclaredFields();
		// getting info from POJO, information through annotation in method get
		for (Field field : fields) {
			Annotation[] annotations = field.getAnnotations();
			for (Annotation annotation : annotations) {
				if(annotation instanceof FieldGroup) {
					FieldGroup fieldGroup = (FieldGroup) annotation;
					if(fieldGroup.isIncluded()) {
						if(fieldGroup.label().length > 1) {
							for (String groupField : fieldGroup.label()) {
								String[] divide = StringUtils.split(groupField,":");
								if(divide.length == 1){
									String[] label = StringUtils.split(groupField,".");
									result.put(field.getName() + "." + divide[0], 
											com.softtech.kismiss.utils.StringUtils.getLabel(label[label.length-1].trim()));
								}else if(divide.length == 2){
									result.put(field.getName() + "." + divide[0], divide[1]);
								}else{
									throw new KismissException("Declaration of multi group field must be array of 'field name : label'");
								}
							}							
						}else {
							result.put(field.getName(), (String) (StringUtils.isEmpty(fieldGroup.label()[0])?
									com.softtech.kismiss.utils.StringUtils.getLabel(field.getName()):fieldGroup.label()[0]));
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * @param clazz
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws KismissException
	 */
	private Map<String, String> getFieldProperty(Class<?> clazz) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, KismissException {
		Map<String, String> result = new HashMap<String, String>();
		Field[] fields = clazz.getDeclaredFields();
		// getting info from POJO, information through annotation in method get
		for (Field field : fields) {
			Annotation[] annotations = field.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof DynamicField) {
					DynamicField dynamicField = (DynamicField) annotation;
					if (dynamicField.isIncluded()) {
						if (dynamicField.label().length > 1) {
							for (String groupField : dynamicField.label()) {
								String[] divide = StringUtils.split(groupField,
										":");
								if (divide.length == 1) {
									String[] label = StringUtils.split(
											groupField, ".");
									result
											.put(
													field.getName() + "."
															+ divide[0],
													com.softtech.kismiss.utils.StringUtils
															.getLabel(label[label.length - 1]
																	.trim()));
								} else if (divide.length == 2) {
									result.put(field.getName() + "."
											+ divide[0], divide[1]);
								} else {
									throw new KismissException(
											"Declaration of multi group field must be array of 'field name : label'");
								}
							}
						} else {
							result
									.put(
											field.getName(),
											(String) (StringUtils
													.isEmpty(dynamicField.label()[0]) ? com.softtech.kismiss.utils.StringUtils
													.getLabel(field.getName())
													: dynamicField.label()[0]));
						}
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * @param clazz
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws KismissException 
	 */
	public Map<String, String> groupPropertyForClass(Class<?> clazz) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
		KismissException {
		return getGroupProperty(clazz);
	}
	
	/**
	 * @param clazz
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws KismissException
	 */
	public Map<String, String> fieldPropertyForClass(Class<?> clazz) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, KismissException {
		return getFieldProperty(clazz);
	}
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, KismissException {
		Map<String, String> map = FieldUtils.getInstance().groupPropertyForClass(Person.class);	
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println("key :"+entry.getKey() + ";value :"+entry.getValue());
		}
	}
}
