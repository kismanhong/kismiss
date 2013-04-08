package com.softtech.kismiss.model;

/**
 * @author Kisman Hong
 *
 */
public class SubTitleInformation {
	private String fontName = "Calibri";
	private int fontSize = 7;
	private int leftPadding = 3;
	private int rightPadding = 3;
	private int height = 25;
	public String getFontName() {
		return fontName;
	}
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	public int getLeftPadding() {
		return leftPadding;
	}
	public void setLeftPadding(int leftPadding) {
		this.leftPadding = leftPadding;
	}
	public int getRightPadding() {
		return rightPadding;
	}
	public void setRightPadding(int rightPadding) {
		this.rightPadding = rightPadding;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}	
}
