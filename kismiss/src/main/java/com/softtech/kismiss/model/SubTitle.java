package com.softtech.kismiss.model;

import org.apache.commons.lang.StringUtils;

import com.softtech.kismiss.enumer.HorizontalAlignment;

/**
 * @author kismanhong
 * this class is used for getting SubTitle information to be printed
 * given three choice : left, center, or right
 */
public class SubTitle {
	private String leftContent;
	private String centerContent;
	private String rightContent;
	private char separateChar;
	private HorizontalAlignment horizontalAlignment;
	private int rightContentPadding;
	
	public SubTitle(){}
	
	public SubTitle(String leftContent){
		this.leftContent = leftContent;
	}
	
	public SubTitle(String leftContent, String centerContent, String rightContent, char separateChar, int rightContentPadding ){
		this.leftContent = leftContent;
		this.centerContent = centerContent;
		this.rightContent = rightContent;
		this.separateChar = separateChar;
		this.rightContentPadding = rightContentPadding;
		this.horizontalAlignment = HorizontalAlignment.Left;
	}
	
	public SubTitle(String leftContent, String centerContent, String rightContent, int rightContentPadding ){
		this.leftContent = leftContent;
		this.centerContent = centerContent;
		this.rightContent = rightContent;
		this.rightContentPadding = rightContentPadding;
		this.horizontalAlignment = HorizontalAlignment.Left;
	}
	
	public SubTitle(String leftContent, String centerContent, String rightContent, HorizontalAlignment horizontalAlignment ){
		this.leftContent = leftContent;
		this.centerContent = centerContent;
		this.rightContent = rightContent;
		this.horizontalAlignment = horizontalAlignment;
	}
	
	public SubTitle(String content, HorizontalAlignment horizontalAlignment, int rightContentPadding){
		if(horizontalAlignment == HorizontalAlignment.Left){
			this.leftContent = content;
		}else if(horizontalAlignment == HorizontalAlignment.Center){
			this.centerContent = content;
		}else{
			this.rightContent = content;
		}
		this.rightContentPadding = rightContentPadding;
	}
	
	public SubTitle(String content, HorizontalAlignment horizontalAlignment, char separateChar, int rightContentPadding){
		if(horizontalAlignment == HorizontalAlignment.Left){
			this.leftContent = content;
		}else if(horizontalAlignment == HorizontalAlignment.Center){
			this.centerContent = content;
		}else{
			this.rightContent = content;
		}
		this.separateChar = separateChar;
		this.rightContentPadding = rightContentPadding;
	}
	
	public SubTitle(String leftContent, String rightContent, int rightContentPadding ){
		this.leftContent = leftContent;
		this.rightContent = rightContent;
		this.rightContentPadding = rightContentPadding;
		this.horizontalAlignment = HorizontalAlignment.Left;
	}
	
	public SubTitle(String leftContent, String rightContent, char separateChar, int rightContentPadding){
		this.leftContent = leftContent;
		this.rightContent = rightContent;
		this.separateChar = separateChar;
		this.rightContentPadding = rightContentPadding;
		this.horizontalAlignment = HorizontalAlignment.Left;
	}
	

	public SubTitle(String leftContent, String centerContent, String rightContent, char separateChar ){
		this.leftContent = leftContent;
		this.centerContent = centerContent;
		this.rightContent = rightContent;
		this.separateChar = separateChar;	
		this.horizontalAlignment = HorizontalAlignment.Left;
	}
	
	public SubTitle(String leftContent, String centerContent, String rightContent ){
		this.leftContent = leftContent;
		this.centerContent = centerContent;
		this.rightContent = rightContent;
		this.horizontalAlignment = HorizontalAlignment.Right;
	}
	
	public SubTitle(String content, HorizontalAlignment horizontalAlignment){
		if(horizontalAlignment == HorizontalAlignment.Left){
			this.leftContent = content;
		}else if(horizontalAlignment == HorizontalAlignment.Center){
			this.centerContent = content;
		}else{
			this.rightContent = content;
		}		
	}
	
	public SubTitle(String content, HorizontalAlignment horizontalAlignment, char separateChar){
		if(horizontalAlignment == HorizontalAlignment.Left){
			this.leftContent = content;
		}else if(horizontalAlignment == HorizontalAlignment.Center){
			this.centerContent = content;
		}else{
			this.rightContent = content;
		}
		this.separateChar = separateChar;
		
	}
	
	public SubTitle(String leftContent, String rightContent ){
		this.leftContent = leftContent;
		this.rightContent = rightContent;	
		this.horizontalAlignment = HorizontalAlignment.Right;
	}
	
	public SubTitle(String leftContent, String rightContent, char separateChar){
		this.leftContent = leftContent;
		this.rightContent = rightContent;
		this.separateChar = separateChar;
		this.horizontalAlignment = HorizontalAlignment.Left;
	}

	public String getLeftContent() {
		return leftContent;
	}

	public void setLeftContent(String leftContent) {
		this.leftContent = leftContent;
	}

	public String getCenterContent() {
		return centerContent;
	}

	public void setCenterContent(String centerContent) {
		this.centerContent = centerContent;
	}

	public String getRightContent() {
		return rightContent;
	}

	public void setRightContent(String rightContent) {
		this.rightContent = rightContent;
	}

	public char getSeparateChar() {
		return separateChar;
	}

	public void setSeparateChar(char separateChar) {
		this.separateChar = separateChar;
	}

	public HorizontalAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}

	public int getRightContentPadding() {
		return rightContentPadding;
	}

	public void setRightContentPadding(int rightContentPadding) {
		this.rightContentPadding = rightContentPadding;
	}
	
	public String getFlag(){
		if(StringUtils.isNotEmpty(leftContent) && StringUtils.isNotEmpty(centerContent) && StringUtils.isNotEmpty(rightContent)){
			return "123";
		}else if(StringUtils.isNotEmpty(leftContent) && StringUtils.isNotEmpty(centerContent)){
			return "13";
		}else if(StringUtils.isNotEmpty(centerContent) && StringUtils.isNotEmpty(rightContent)){
			return "23";
		}else if(StringUtils.isNotEmpty(leftContent) && StringUtils.isNotEmpty(rightContent)){
			return "13";
		}else if(StringUtils.isNotEmpty(leftContent)){
			return "1";
		}else if(StringUtils.isNotEmpty(centerContent)){
			return "2";
		}else if(StringUtils.isNotEmpty(rightContent)){
			return "3";
		}
		return "";
	}

}
