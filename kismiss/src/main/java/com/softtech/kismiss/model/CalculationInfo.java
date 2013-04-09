package com.softtech.kismiss.model;

import com.softtech.kismiss.enumer.ColorMode;
import com.softtech.kismiss.enumer.FontType;
import com.softtech.kismiss.enumer.HorizontalAlignment;
import com.softtech.kismiss.enumer.LineStyle;
import com.softtech.kismiss.enumer.VerticalAlignment;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * simplify report generation
 * Calculation is used for placing the attributes that 
 * defined for calculation in jasper report (Sum,Average,Lowest,Highest)
 * this is used for java 1.5 above
 * 
 */
public class CalculationInfo {
	
	private String attribute;
	private String calculationType;
	private int xValue;
	private int yValue;
	private int width;
	private String dataType;

	private int height;
	private String calName;	
	private String calGroupName;
	
	private FontType fontType						=FontType.Arial;
	private int fontSize 							= 8;
	private VerticalAlignment verticalAlignment 	= VerticalAlignment.Middle;
	private HorizontalAlignment horizontalAlignment = HorizontalAlignment.Center;
	private HorizontalAlignment labelHorizontalAlignment = HorizontalAlignment.Center;
	private boolean bold;
	private double lineWidth 						= 0.5;
	private LineStyle lineStyle 					= LineStyle.Solid;
	private String pattern 							= "#,##0.00";
	private String label 							= "";
	private String backColor						= "";
	private ColorMode colorMode						= ColorMode.Transparent;
	
	/**
	 * default constructor
	 */
	public CalculationInfo() {
		
	}

	
	public CalculationInfo(String attribute, String dataType, String calculationType, int xValue, int yValue, int width, int height,
			FontType fontType, int fontSize, VerticalAlignment verticalAlignment, HorizontalAlignment horizontalAlignment, boolean bold,
			double lineWidth, LineStyle lineStyle, String pattern, String label, String backColor, ColorMode colorMode, HorizontalAlignment 
			labelHorizontalAlignment)
	{
		setAttribute(attribute);
		setCalculationType(calculationType);
		setXValue(xValue);
		setYValue(yValue);
		setWidth(width);
		setHeight(height);
		setCalName(attribute+calculationType);
		setCalGroupName(attribute+calculationType+"AdvanceCal");
		setDataType(dataType);
		setFontSize(fontSize);
		setFontType(fontType);
		setVerticalAlignment(verticalAlignment);
		setHorizontalAlignment(horizontalAlignment);
		setBold(bold);
		setLineStyle(lineStyle);
		setLineWidth(lineWidth);
		setPattern(pattern);
		setLabel(label);
		setBackColor(backColor);
		setColorMode(colorMode);
		setLabelHorizontalAlignment(labelHorizontalAlignment);
	}
	
	public CalculationInfo(String attribute, String dataType, String calculationType, int xValue, int yValue, int width, int height, double lineWidth,
			HorizontalAlignment horizontalAlignment, HorizontalAlignment labelHorizontalAlignment, String pattern)
	{
		setAttribute(attribute);
		setCalculationType(calculationType);
		setXValue(xValue);
		setYValue(yValue);
		setWidth(width);
		setHeight(height);
		setCalName(attribute+calculationType);
		setCalGroupName(attribute+calculationType+"Advance");
		setDataType(dataType);
		setLineWidth(lineWidth);
		setHorizontalAlignment(horizontalAlignment);
		setLabelHorizontalAlignment(labelHorizontalAlignment);
		setPattern(pattern);
	}

	public CalculationInfo(String attribute, String dataType, String calculationType, int xValue, int yValue, int width, int height, double lineWidth,
			HorizontalAlignment horizontalAlignment, HorizontalAlignment labelHorizontalAlignment)
	{
		setAttribute(attribute);
		setCalculationType(calculationType);
		setXValue(xValue);
		setYValue(yValue);
		setWidth(width);
		setHeight(height);
		setCalName(attribute+calculationType);
		setCalGroupName(attribute+calculationType+"Advance");
		setDataType(dataType);
		setLineWidth(lineWidth);
		setHorizontalAlignment(horizontalAlignment);
		setLabelHorizontalAlignment(labelHorizontalAlignment);
	}
	

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((attribute == null) ? 0 : attribute.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CalculationInfo other = (CalculationInfo) obj;
		if (attribute == null) {
			if (other.attribute != null)
				return false;
		} else if (!attribute.equals(other.attribute))
			return false;
		return true;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getCalculationType() {
		return calculationType;
	}

	public void setCalculationType(String calculationType) {
		this.calculationType = calculationType;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getXValue() {
		return xValue;
	}

	public void setXValue(int value) {
		xValue = value;
	}

	public int getYValue() {
		return yValue;
	}

	public void setYValue(int value) {
		yValue = value;
	}
	
	public String getCalName() {
		return calName;
	}

	public void setCalName(String calName) {
		this.calName = calName;
	}
	
	public String getCalGroupName() {
		return calGroupName;
	}

	public void setCalGroupName(String calGroupName) {
		this.calGroupName = calGroupName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public FontType getFontType() {
		return fontType;
	}

	public void setFontType(FontType fontType) {
		this.fontType = fontType;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public VerticalAlignment getVerticalAlignment() {
		return verticalAlignment;
	}

	public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	public HorizontalAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}

	public double getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(double lineWidth) {
		this.lineWidth = lineWidth;
	}

	

	public LineStyle getLineStyle() {
		return lineStyle;
	}


	public void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}


	public String getPattern() {
		return pattern;
	}


	public void setPattern(String pattern) {
		this.pattern = pattern;
	}


	public String getLabel() {
		return label;
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


	public ColorMode getColorMode() {
		return colorMode;
	}


	public void setColorMode(ColorMode colorMode) {
		this.colorMode = colorMode;
	}


	public HorizontalAlignment getLabelHorizontalAlignment() {
		return labelHorizontalAlignment;
	}


	public void setLabelHorizontalAlignment(
			HorizontalAlignment labelHorizontalAlignment) {
		this.labelHorizontalAlignment = labelHorizontalAlignment;
	}
	
	

}
