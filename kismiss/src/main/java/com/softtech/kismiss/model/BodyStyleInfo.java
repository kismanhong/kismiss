package com.softtech.kismiss.model;

/**
 * @author kismanhong
 *
 */
public class BodyStyleInfo {
	private String name;
	private boolean isDefault;
	private String backColor;
	private boolean isStyledText;
	
	public BodyStyleInfo(String name, boolean isDefault, String backColor,
			boolean isStyledText) {
		super();
		this.name = name;
		this.isDefault = isDefault;
		this.backColor = backColor;
		this.isStyledText = isStyledText;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	public String getBackColor() {
		return backColor;
	}
	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}
	public boolean isStyledText() {
		return isStyledText;
	}
	public void setStyledText(boolean isStyledText) {
		this.isStyledText = isStyledText;
	}
	
	
}
