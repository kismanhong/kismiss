package com.softtech.kismiss.access;

import java.util.List;

import com.softtech.kismiss.enumer.CalculationPrintType;
import com.softtech.kismiss.enumer.FontType;
import com.softtech.kismiss.enumer.HorizontalAlignment;
import com.softtech.kismiss.enumer.LineStyle;
import com.softtech.kismiss.enumer.VerticalAlignment;


/**
 * @author Kisman Hong
 * Softtech Inc. Dream Company
 * Group for declaring Group from client
 *
 */
public class Group {
	private String groupBy;
	private List<Calculation> calculations;
	private int bandHeight							= 20;
	private int height								= 20;
	private String underGroup;
	private int fontSize 							= 8;
	private FontType font 							= FontType.Arial;
	private int leftPadding 						= 10;
	private int rightPadding 						= 10;
	private double lineWidth 						= -1;
	private LineStyle lineStyle 					= LineStyle.Solid;
	private VerticalAlignment verticalAlignment 	= VerticalAlignment.Middle;
	private HorizontalAlignment horizontalAlignment = HorizontalAlignment.Left;
	private HorizontalAlignment labelHorizontalAlignment = HorizontalAlignment.Center;
	private CalculationPrintType calculationPrintType = CalculationPrintType.GroupExpression;
	private boolean bold							= true;
	private String backColor						= "";				
	
	public Group(String groupBy, List<Calculation> calculations, int bandHeight, int height, String underGroup)
	{
		this.groupBy 		= groupBy;
		this.calculations 	= calculations;
		this.bandHeight 	= bandHeight;
		this.height 		= height;
		this.underGroup 	= underGroup;
	}
	
	public Group(String groupBy, List<Calculation> calculations, int bandHeight, int height)
	{
		this.groupBy 		= groupBy;
		this.calculations 	= calculations;
		this.bandHeight 	= bandHeight;
		this.height 		= height;
	}
	
	public Group(String groupBy, List<Calculation> calculations)
	{
		this.groupBy 		= groupBy;
		this.calculations 	= calculations;
	}
	
	public Group(String groupBy, List<Calculation> calculations, String underGroup)
	{
		this.groupBy 		= groupBy;
		this.calculations 	= calculations;
		this.underGroup 	= underGroup;
	}
	
	public Group(String groupBy)
	{
		this.groupBy 		= groupBy;
	}
	
	public String getGroupBy() {
		return groupBy;
	}
	public List<Calculation> getCalculations() {
		return calculations;
	}
	
	public int getBandHeight() {
		return bandHeight;
	}

	public int getHeight() {
		return height;
	}
	public String getUnderGroup() {
		return underGroup;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public FontType getFont() {
		return font;
	}

	public void setFont(FontType font) {
		this.font = font;
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

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public void setCalculations(List<Calculation> calculations) {
		this.calculations = calculations;
	}

	public void setBandHeight(int bandHeight) {
		this.bandHeight = bandHeight;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setUnderGroup(String underGroup) {
		this.underGroup = underGroup;
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

	public String getBackColor() {
		return backColor;
	}

	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}
	
	public HorizontalAlignment getLabelHorizontalAlignment() {
		return labelHorizontalAlignment;
	}

	public void setLabelHorizontalAlignment(HorizontalAlignment labelHorizontalAlignment) {
		this.labelHorizontalAlignment = labelHorizontalAlignment;
	}
	
}
