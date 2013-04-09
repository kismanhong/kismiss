package com.softtech.kismiss.enumer;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * Jasper ireport calculation type
 *
 */
public enum CalculationType {
	Sum,
	Average,
	Highest,
	Lowest,
	Count,
	DistinctCount;
	
	public static boolean contains(String calculationType) {
	    for (CalculationType c : CalculationType.values()) {
	        if (c.name().equals(calculationType)) {
	            return true;
	        }
	    }
	    return false;
	}
}
