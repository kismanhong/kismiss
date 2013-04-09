package com.softtech.kismiss.model;

import java.util.List;

import com.softtech.kismiss.enumer.CalculationPrintType;
import com.softtech.kismiss.enumer.ColorMode;
import com.softtech.kismiss.enumer.FontType;
import com.softtech.kismiss.enumer.HorizontalAlignment;
import com.softtech.kismiss.enumer.LineStyle;
import com.softtech.kismiss.enumer.VerticalAlignment;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * Softtech Inc. Dream Company
 * simplify report generation
 * handling Group information using this class
 * this is used for java 1.5 above
 */
public class GroupInfo {
	private String attributeName;
	private int bandHeight;
	private int xValue;
	private int yValue;
	private int height;
	private int width;
	private boolean bold;
	private FontType fontType;
	private int fontSize;
	private List<CalculationInfo> groupCals;
	private CalculationPrintType calculationPrintType;
	private int xLeftest;
	private int leftPadding;
	private double lineWidth;
	private LineStyle lineStyle;
	private String backColor;
	private ColorMode colorMode;
	private HorizontalAlignment horizontalAlignment;
	private HorizontalAlignment labelHorizontalAlignment;
	private VerticalAlignment verticalAlignment;
	private String groupLabel;
	private String labelPrefix;
	private String labelSuffix;
	
	public GroupInfo(String attributeName){
		this.attributeName = attributeName;
	}
	
	/**
	 * @param attributeName
	 * @param bandHeight
	 * @param xValue
	 * @param yValue
	 * @param height
	 * @param width
	 * @param groupCals
	 */
	public GroupInfo(String attributeName, int bandHeight, int xValue, int yValue, int height, int width, List<CalculationInfo> groupCals, 
			CalculationPrintType calculationPrintType, boolean bold, FontType fontType, int fontSize, int leftPadding, double lineWidth, 
			String backColor, ColorMode colorMode, HorizontalAlignment horizontalAlignment, HorizontalAlignment labelHorizontalAlignment,
			LineStyle lineStyle, VerticalAlignment verticalAlignment, String groupLabel, String labelPrefix, String labelSuffix)
	{
		setAttributeName(attributeName);
		setBandHeight(bandHeight);
		setXValue(xValue);
		setYValue(yValue);
		setHeight(height);
		setWidth(width);
		setGroupCals(groupCals);
		setCalculationPrintType(calculationPrintType);
		setBold(bold);
		setFontSize(fontSize);
		setFontType(fontType);
		setLeftPadding(leftPadding);
		setLineWidth(lineWidth);
		setBackColor(backColor);
		setColorMode(colorMode);
		setHorizontalAlignment(horizontalAlignment);
		setLabelHorizontalAlignment(labelHorizontalAlignment);
		setLineStyle(lineStyle);
		setVerticalAlignment(verticalAlignment);
		setGroupLabel(groupLabel);
		setLabelPrefix(labelPrefix);
		setLabelSuffix(labelSuffix);
	}
	
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public int getBandHeight() {
		return bandHeight;
	}
	public void setBandHeight(int bandHeight) {
		this.bandHeight = bandHeight;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
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
	public List<CalculationInfo> getGroupCals() {
		return groupCals;
	}

	public void setGroupCals(List<CalculationInfo> groupCals) {
		this.groupCals = groupCals;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getxLeftest() {
		return xLeftest;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attributeName == null) ? 0 : attributeName.hashCode());
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
		GroupInfo other = (GroupInfo) obj;
		if (attributeName == null) {
			if (other.attributeName != null)
				return false;
		} else if (!attributeName.equals(other.attributeName))
			return false;
		return true;
	}

	public void setxLeftest(int xLeftest) {
		this.xLeftest = xLeftest;
	}

	public CalculationPrintType getCalculationPrintType() {
		return calculationPrintType;
	}

	public void setCalculationPrintType(CalculationPrintType calculationPrintType) {
		this.calculationPrintType = calculationPrintType;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
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

	public int getLeftPadding() {
		return leftPadding;
	}

	public void setLeftPadding(int leftPadding) {
		this.leftPadding = leftPadding;
	}

	public double getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(double lineWidth) {
		this.lineWidth = lineWidth;
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

	public HorizontalAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}

	public HorizontalAlignment getLabelHorizontalAlignment() {
		return labelHorizontalAlignment;
	}

	public void setLabelHorizontalAlignment(
			HorizontalAlignment labelHorizontalAlignment) {
		this.labelHorizontalAlignment = labelHorizontalAlignment;
	}

	public LineStyle getLineStyle() {
		return lineStyle;
	}
	public void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}

	public VerticalAlignment getVerticalAlignment() {
		return verticalAlignment;
	}

	public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	public String getGroupLabel() {
		return groupLabel;
	}

	public void setGroupLabel(String groupLabel) {
		this.groupLabel = groupLabel;
	}

	public String getLabelPrefix() {
		return labelPrefix;
	}

	public void setLabelPrefix(String labelPrefix) {
		this.labelPrefix = labelPrefix;
	}

	public String getLabelSuffix() {
		return labelSuffix;
	}

	public void setLabelSuffix(String labelSuffix) {
		this.labelSuffix = labelSuffix;
	}
	
}
