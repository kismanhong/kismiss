package com.softtech.kismiss.access;

import com.softtech.kismiss.enumer.CalculationType;
import com.softtech.kismiss.enumer.FontType;
import com.softtech.kismiss.enumer.HorizontalAlignment;
import com.softtech.kismiss.enumer.LineStyle;
import com.softtech.kismiss.enumer.VerticalAlignment;

/**
 * @author Kisman Hong
 * specify your calculation information
 *
 */
public class Calculation {
	
	private String attribute;
	private CalculationType calculationType;
	private FontType fontType 						= FontType.Arial;
	private int fontSize 							= 8;
	private VerticalAlignment verticalAlignment 	= VerticalAlignment.Middle;
	private HorizontalAlignment horizontalAlignment = HorizontalAlignment.Center;
	private HorizontalAlignment labelHorizontalAlignment = HorizontalAlignment.Center;
	private boolean bold 							= false;
	private double lineWidth 						= 0.5;
	private LineStyle lineStyle 					= LineStyle.Solid;
	private String pattern 							= "#,##0.00";
	private String label							= "";
	private String backColor						= "";
	
	public Calculation()
	{
		
	}
	
	public Calculation(String attribute, CalculationType calculationType)
	{
		this.attribute 		 	= attribute;
		this.calculationType    = calculationType;
	}
	
	public Calculation(String attribute, CalculationType calculationType, FontType fontType, int fontSize, boolean bold, String pattern, String label)
	{
		this.attribute 		 	= attribute;
		this.calculationType 	= calculationType;
		this.fontType			= fontType;
		this.fontSize			= fontSize;
		this.bold				= bold;
		this.pattern			= pattern;
		this.label				= label;
	}
	
	public Calculation(String attribute, CalculationType calculationType, FontType fontType, int fontSize, String pattern, String label, VerticalAlignment verticalAlignment, HorizontalAlignment horizontalAlignment)
	{
		this.attribute 		 	= attribute;
		this.calculationType 	= calculationType;
		this.fontType			= fontType;
		this.fontSize			= fontSize;
		this.verticalAlignment 	= verticalAlignment;
		this.horizontalAlignment= horizontalAlignment;
		this.pattern			= pattern;
		this.label				= label;
	}
	
	public Calculation(String attribute, CalculationType calculationType, FontType fontType, int fontSize, String pattern, String label, VerticalAlignment verticalAlignment, HorizontalAlignment horizontalAlignment, double lineWidth, LineStyle lineStyle)
	{
		this.attribute 		 	= attribute;
		this.calculationType 	= calculationType;
		this.fontType			= fontType;
		this.fontSize			= fontSize;
		this.verticalAlignment 	= verticalAlignment;
		this.horizontalAlignment= horizontalAlignment;
		this.lineWidth			= lineWidth;
		this.lineStyle			= lineStyle;
		this.pattern			= pattern;
		this.label				= label;
	}
	
	public Calculation(String attribute, CalculationType calculationType, String pattern, String label, VerticalAlignment verticalAlignment, HorizontalAlignment horizontalAlignment)
	{
		this.attribute 		 	= attribute;
		this.calculationType 	= calculationType;
		this.verticalAlignment 	= verticalAlignment;
		this.horizontalAlignment= horizontalAlignment;
		this.pattern			= pattern;
		this.label				= label;
	}

	public String getAttribute() {
		return attribute;
	}

	public CalculationType getCalculationType() {
		return calculationType;
	}

	public FontType getFontType() {
		return fontType;
	}

	public int getFontSize() {
		return fontSize;
	}

	public VerticalAlignment getVerticalAlignment() {
		return verticalAlignment;
	}

	public HorizontalAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	public boolean isBold() {
		return bold;
	}

	public double getLineWidth() {
		return lineWidth;
	}

	public LineStyle getLineStyle() {
		return lineStyle;
	}

	public String getPattern() {
		return pattern;
	}

	public String getLabel() {
		return label;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public void setCalculationType(CalculationType calculationType) {
		this.calculationType = calculationType;
	}

	public void setFontType(FontType fontType) {
		this.fontType = fontType;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}

	public void setLineWidth(double lineWidth) {
		this.lineWidth = lineWidth;
	}

	public void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getBackColor() {
		return backColor;
	}

	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}

	public HorizontalAlignment getLabelHorizontalAlignment() {
		return labelHorizontalAlignment;
	}

	public void setLabelHorizontalAlignment(
			HorizontalAlignment labelHorizontalAlignment) {
		this.labelHorizontalAlignment = labelHorizontalAlignment;
	}
	
	
	
}