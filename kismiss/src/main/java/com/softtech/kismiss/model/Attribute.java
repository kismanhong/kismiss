package com.softtech.kismiss.model;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * simplify report generation
 * Attribute is used for placing the all attribute from object, 
 * set by reflection method
 * this is used for java 1.5 above
 */
public class Attribute {
	private String name;

	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
