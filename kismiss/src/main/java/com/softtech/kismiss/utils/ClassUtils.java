package com.softtech.kismiss.utils;

import java.net.URL;

import org.apache.commons.lang.StringUtils;

import com.softtech.kismiss.exception.KismissException;

public class ClassUtils {
	private static final String KISMISS_FILE = "_kismiss.jasper";
	private static final String DOT_CLASS = ".class";
	
	public static String getFileLocation(Class<?> clazz) throws KismissException{
		String result = "";
		 final URL location;
		    final String classLocation = clazz.getName().replace('.', '/') + ".class";
		    final ClassLoader loader = clazz.getClassLoader();
		    if (loader == null) {
		     throw new KismissException("Cannot find class : {}", clazz.getName());
		   } else {
		      location = loader.getResource(classLocation);
		      result = location.getPath();
		    }
		  return StringUtils.replace(result, DOT_CLASS, KISMISS_FILE);
	}
}
