package com.softtech.kismiss.model;

import java.util.List;

import com.softtech.kismiss.enumer.ColorMode;
import com.softtech.kismiss.enumer.FontType;
import com.softtech.kismiss.enumer.HorizontalAlignment;
import com.softtech.kismiss.enumer.LineSpace;
import com.softtech.kismiss.enumer.Rotation;
import com.softtech.kismiss.enumer.ScaleImageType;
import com.softtech.kismiss.enumer.VerticalAlignment;

/**
 * @author kismanhong
 *
 */
public class StyleInfo {
	private  String name;
	private  boolean isDefault;
	private  String style;
	private  ColorMode mode;
	private  String foreColor;
	private  String backColor;
	private  String pen;
	private  String fill;
	private  String radius;
	private  ScaleImageType scaleImage;
	private  HorizontalAlignment hAlign;
	private  VerticalAlignment vAlign;
	private  String border;
	private  String borderColor;
	private  int padding;
	private  String topBorder;
	private  String topBorderColor;
	private  int topPadding;
	private  String leftBorder;
	private  String leftBorderColor;
	private  int leftPadding;
	private  String bottomBorder;
	private  String bottomBorderColor;
	private  int bottomPadding;
	private  String rightBorder;
	private  String rightBorderColor;
	private  int rightPadding;
	private  Rotation rotation;
	private  LineSpace lineSpace;
	private  boolean isStyledText;
	private  FontType fontName;
	private  int fontSize;
	private  boolean isBold;
	private  boolean isUnderline;
	private  boolean isItalic;
	private  boolean isStrikeThrough;
	private  FontType pdfFontName;
	private  String pdfEncoding;
	private  boolean isPdfEmbedded;
	private  String pattern;
	private  boolean isBlankWhenNull;
//	private  String conditionalExpression;
	private List<ConditionalStyleInfo> conditionalStyleInfos;
	
	public StyleInfo(String name, boolean isDefault, // String style,
			ColorMode mode, String foreColor, String backColor, String pen,
			String fill, String radius, ScaleImageType scaleImage,
			HorizontalAlignment hAlign, VerticalAlignment vAlign,
			String border, String borderColor, int padding, String topBorder,
			String topBorderColor, int topPadding, String leftBorder,
			String leftBorderColor, int leftPadding, String bottomBorder,
			String bottomBorderColor, int bottomPadding, String rightBorder,
			String rightBorderColor, int rightPadding, Rotation rotation,
			LineSpace lineSpace, boolean isStyledText, FontType fontName,
			int fontSize, boolean isBold, boolean isUnderline,
			boolean isItalic, boolean isStrikeThrough, FontType pdfFontName,
			String pdfEncoding, boolean isPdfEmbedded, String pattern,
			boolean isBlankWhenNull ) //, String conditionalExpression)
	{
		super();
		this.name = name;
		this.isDefault = isDefault;
//		this.style = style;
		this.mode = mode;
		this.foreColor = foreColor;
		this.backColor = backColor;
		this.pen = pen;
		this.fill = fill;
		this.radius = radius;
		this.scaleImage = scaleImage;
		this.hAlign = hAlign;
		this.vAlign = vAlign;
		this.border = border;
		this.borderColor = borderColor;
		this.padding = padding;
		this.topBorder = topBorder;
		this.topBorderColor = topBorderColor;
		this.topPadding = topPadding;
		this.leftBorder = leftBorder;
		this.leftBorderColor = leftBorderColor;
		this.leftPadding = leftPadding;
		this.bottomBorder = bottomBorder;
		this.bottomBorderColor = bottomBorderColor;
		this.bottomPadding = bottomPadding;
		this.rightBorder = rightBorder;
		this.rightBorderColor = rightBorderColor;
		this.rightPadding = rightPadding;
		this.rotation = rotation;
		this.lineSpace = lineSpace;
		this.isStyledText = isStyledText;
		this.fontName = fontName;
		this.fontSize = fontSize;
		this.isBold = isBold;
		this.isUnderline = isUnderline;
		this.isItalic = isItalic;
		this.isStrikeThrough = isStrikeThrough;
		this.pdfFontName = pdfFontName;
		this.pdfEncoding = pdfEncoding;
		this.isPdfEmbedded = isPdfEmbedded;
		this.pattern = pattern;
		this.isBlankWhenNull = isBlankWhenNull;
//		this.conditionalExpression = conditionalExpression;
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
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public ColorMode getMode() {
		return mode;
	}
	public void setMode(ColorMode mode) {
		this.mode = mode;
	}
	public String getForeColor() {
		return foreColor;
	}
	public void setForeColor(String foreColor) {
		this.foreColor = foreColor;
	}
	public String getBackColor() {
		return backColor;
	}
	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}
	public String getPen() {
		return pen;
	}
	public void setPen(String pen) {
		this.pen = pen;
	}
	public String getFill() {
		return fill;
	}
	public void setFill(String fill) {
		this.fill = fill;
	}
	public String getRadius() {
		return radius;
	}
	public void setRadius(String radius) {
		this.radius = radius;
	}
	public ScaleImageType getScaleImage() {
		return scaleImage;
	}
	public void setScaleImage(ScaleImageType scaleImage) {
		this.scaleImage = scaleImage;
	}
	public HorizontalAlignment gethAlign() {
		return hAlign;
	}
	public void sethAlign(HorizontalAlignment hAlign) {
		this.hAlign = hAlign;
	}
	public VerticalAlignment getvAlign() {
		return vAlign;
	}
	public void setvAlign(VerticalAlignment vAlign) {
		this.vAlign = vAlign;
	}
	public String getBorder() {
		return border;
	}
	public void setBorder(String border) {
		this.border = border;
	}
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public int getPadding() {
		return padding;
	}
	public void setPadding(int padding) {
		this.padding = padding;
	}
	public String getTopBorder() {
		return topBorder;
	}
	public void setTopBorder(String topBorder) {
		this.topBorder = topBorder;
	}
	public String getTopBorderColor() {
		return topBorderColor;
	}
	public void setTopBorderColor(String topBorderColor) {
		this.topBorderColor = topBorderColor;
	}
	public int getTopPadding() {
		return topPadding;
	}
	public void setTopPadding(int topPadding) {
		this.topPadding = topPadding;
	}
	public String getLeftBorder() {
		return leftBorder;
	}
	public void setLeftBorder(String leftBorder) {
		this.leftBorder = leftBorder;
	}
	public String getLeftBorderColor() {
		return leftBorderColor;
	}
	public void setLeftBorderColor(String leftBorderColor) {
		this.leftBorderColor = leftBorderColor;
	}
	public int getLeftPadding() {
		return leftPadding;
	}
	public void setLeftPadding(int leftPadding) {
		this.leftPadding = leftPadding;
	}
	public String getBottomBorder() {
		return bottomBorder;
	}
	public void setBottomBorder(String bottomBorder) {
		this.bottomBorder = bottomBorder;
	}
	public String getBottomBorderColor() {
		return bottomBorderColor;
	}
	public void setBottomBorderColor(String bottomBorderColor) {
		this.bottomBorderColor = bottomBorderColor;
	}
	public int getBottomPadding() {
		return bottomPadding;
	}
	public void setBottomPadding(int bottomPadding) {
		this.bottomPadding = bottomPadding;
	}
	public String getRightBorder() {
		return rightBorder;
	}
	public void setRightBorder(String rightBorder) {
		this.rightBorder = rightBorder;
	}
	public String getRightBorderColor() {
		return rightBorderColor;
	}
	public void setRightBorderColor(String rightBorderColor) {
		this.rightBorderColor = rightBorderColor;
	}
	public int getRightPadding() {
		return rightPadding;
	}
	public void setRightPadding(int rightPadding) {
		this.rightPadding = rightPadding;
	}
	public Rotation getRotation() {
		return rotation;
	}
	public void setRotation(Rotation rotation) {
		this.rotation = rotation;
	}
	public LineSpace getLineSpace() {
		return lineSpace;
	}
	public void setLineSpace(LineSpace lineSpace) {
		this.lineSpace = lineSpace;
	}
	public boolean isStyledText() {
		return isStyledText;
	}
	public void setStyledText(boolean isStyledText) {
		this.isStyledText = isStyledText;
	}
	public FontType getFontName() {
		return fontName;
	}
	public void setFontName(FontType fontName) {
		this.fontName = fontName;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	public boolean isBold() {
		return isBold;
	}
	public void setBold(boolean isBold) {
		this.isBold = isBold;
	}
	public boolean isUnderline() {
		return isUnderline;
	}
	public void setUnderline(boolean isUnderline) {
		this.isUnderline = isUnderline;
	}
	public boolean isItalic() {
		return isItalic;
	}
	public void setItalic(boolean isItalic) {
		this.isItalic = isItalic;
	}
	public boolean isStrikeThrough() {
		return isStrikeThrough;
	}
	public void setStrikeThrough(boolean isStrikeThrough) {
		this.isStrikeThrough = isStrikeThrough;
	}
	public FontType getPdfFontName() {
		return pdfFontName;
	}
	public void setPdfFontName(FontType pdfFontName) {
		this.pdfFontName = pdfFontName;
	}
	public String getPdfEncoding() {
		return pdfEncoding;
	}
	public void setPdfEncoding(String pdfEncoding) {
		this.pdfEncoding = pdfEncoding;
	}
	public boolean isPdfEmbedded() {
		return isPdfEmbedded;
	}
	public void setPdfEmbedded(boolean isPdfEmbedded) {
		this.isPdfEmbedded = isPdfEmbedded;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public boolean isBlankWhenNull() {
		return isBlankWhenNull;
	}
	public void setBlankWhenNull(boolean isBlankWhenNull) {
		this.isBlankWhenNull = isBlankWhenNull;
	}
//	public String getConditionalExpression() {
//		return conditionalExpression;
//	}
//	public void setConditionalExpression(String conditionalExpression) {
//		this.conditionalExpression = conditionalExpression;
//	}
	public List<ConditionalStyleInfo> getConditionalStyleInfos() {
		return conditionalStyleInfos;
	}
	public void setConditionalStyleInfos(
			List<ConditionalStyleInfo> conditionalStyleInfos) {
		this.conditionalStyleInfos = conditionalStyleInfos;
	}
	
}
