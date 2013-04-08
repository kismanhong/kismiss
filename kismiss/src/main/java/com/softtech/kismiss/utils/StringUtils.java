package com.softtech.kismiss.utils;

/**
 * @author Kisman Hong
 *	manipulation String
 */
public class StringUtils {
	public static String getLabel(String label) {
		return org.apache.commons.lang.StringUtils.capitalize(divideString(label));
	}

	private static String divideString(String label) {
		String result = "";
		for (int i = 0; i < label.length(); i++) {
			int ascii = (int) label.charAt(i);
			if (ascii < 97 || ascii > 122)
				result += " ";
			result += label.charAt(i);
		}
		return result;
	}
}
