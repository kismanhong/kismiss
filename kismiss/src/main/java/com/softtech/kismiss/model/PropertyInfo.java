package com.softtech.kismiss.model;

import org.apache.commons.lang.StringUtils;

import com.softtech.kismiss.enumer.ColorMode;
import com.softtech.kismiss.enumer.CrosstabFunctionType;
import com.softtech.kismiss.enumer.CrosstabGroupByType;
import com.softtech.kismiss.enumer.HorizontalAlignment;
import com.softtech.kismiss.enumer.VerticalAlignment;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * simplify report generation
 * AttributeInfo is used for placing the attributes information from user
 * this is used for java 1.5 above
 */
public class PropertyInfo implements Cloneable{
	// private static AttributeInfo instance = null;
	
	private String attributeName;
	private boolean overFlow;
	private boolean isBlankWhenNull;
	private String evaluationTime;
	private String hyperlinkType;
	
	private String hyperlinkTarget;
	private int fontSize;
	private String font;
	private int leftPadding;
	private int rightPadding;
	
	private int topPadding;
	private int bottomPadding;
	private double lineWidth;
	private String lineStyle;
	private String pattern;
	
	private int x;	
	private int y;
	private int width;
	private int height;
	private String key;
	
	private String label;	
	private int column;
	private HorizontalAlignment horizontalAlignment;
	private VerticalAlignment verticalAlignment;
	private String type;
	
	private String[] columnHierarchy;
	private String bgColor;
	private String textFieldExpression;
	private String printWhenExpression;
	private boolean isPrintRepeatedValues;

	private ColorMode colorMode;

	private int widthPortion;
	private int[] heightPortion;
	
	private boolean recordNumber;
	private boolean resetWhenGrouped;
	private String resetGroupName;
	
	private String border;
	private String borderColor;
	private int padding;
	private String topBorder;
	private String topBorderColor;
	private String leftBorder;
	private String leftBorderColor;
	private String rightBorder;
	private String rightBorderColor;
	private String bottomBorder;
	private String bottomBorderColor;		
	
	/* crosstab */
	private boolean isShownWhenCrosstab;
	private boolean rowGroup;
	private boolean columnGroup;
	private CrosstabFunctionType crosstabFunction;
	private CrosstabGroupByType crosstabGroupBy;
	int crosstabCellWidth;
	
	private String prefix;
	private String postfix;
	
	private boolean isShowInDetail;
	private boolean isPrintWhenDetailOverFlow;
	private boolean isRemoveLineWhenBlank;
	private boolean isBold;
	private boolean isUnderLine;
	private boolean isItalic;
	private boolean isStrikeThrough;
	
	private String style;
	private String foreColor;
	private HorizontalAlignment whenHeaderHAlignment;
	
	public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            // This should never happen
            throw new InternalError(e.toString());
        }
    }


	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((attributeName == null) ? 0 : attributeName.hashCode());
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
		final PropertyInfo other = (PropertyInfo) obj;
		if (attributeName == null) {
			if (other.attributeName != null)
				return false;
		} else if (!attributeName.equals(other.attributeName))
			return false;
//		else if (!abovePosition.equals(other.abovePosition))
//			return false;
		return true;
	}

	
	public PropertyInfo(
			String attributeName, int x, int y, int width,
			int height, String key, String label, int column,
			boolean isOverFlow, boolean isBlankWhenNull, String evaluationTime,
			String hyperlinkType, String hyperlinkTarget, int fontSize,
			String font, String bgColor, ColorMode colorMode, int leftPadding, int rightPadding, int topPadding,
			int bottomPadding, double lineWidth, String lineStyle, HorizontalAlignment horizontalAlignment,
			VerticalAlignment verticalAlignment,
			String[] columnHierarchy, int widthPortion, int[] heightPortion,
			String pattern, String type, boolean recordNumber //, boolean overFlow
			) {
				this.attributeName = attributeName;
				this.x = x;
				this.y = y;
				this.width = width;
				
				this.height = height;
				this.key = key;
				this.label = StringUtils.isEmpty(label)?com.softtech.kismiss.utils.StringUtils.getLabel(attributeName):label;
				this.column = column;
				this.overFlow = isOverFlow;
				
				this.isBlankWhenNull = isBlankWhenNull;
				this.evaluationTime = evaluationTime;
				this.hyperlinkType = hyperlinkType;
				this.hyperlinkTarget = hyperlinkTarget;
				this.fontSize = fontSize;
				
				this.font = font;
				this.bgColor = bgColor;
				this.colorMode = StringUtils.isEmpty(bgColor)?colorMode: ColorMode.Opaque;
				this.leftPadding = leftPadding;
				this.rightPadding = rightPadding;
				
				this.topPadding = topPadding;
				this.bottomPadding = bottomPadding;
				this.lineWidth = lineWidth;
				this.lineStyle = lineStyle;
				this.horizontalAlignment = horizontalAlignment;
				
				this.verticalAlignment = verticalAlignment;
				this.columnHierarchy = columnHierarchy;
				this.widthPortion = widthPortion;
				this.heightPortion = heightPortion;
				this.pattern = pattern;
				
				this.type = type;
				this.recordNumber = recordNumber;
	}

	public PropertyInfo(
			String attributeName, int x, int y, int width,
			int height, String key, String label, int column,
			boolean isOverFlow, boolean isBlankWhenNull, String evaluationTime,
			String hyperlinkType, String hyperlinkTarget, int fontSize,
			String font, String bgColor, ColorMode colorMode, int leftPadding, int rightPadding, int topPadding,
			int bottomPadding, double lineWidth, String lineStyle, HorizontalAlignment horizontalAlignment,
			VerticalAlignment verticalAlignment,
			String[] columnHierarchy, int widthPortion, int[] heightPortion,
			String pattern, String type, boolean recordNumber, boolean isResetWhenGrouped, String resetGroupName,
			String border, String borderColor, int padding,
			String topBorder, String topBorderColor, String leftBorder, String leftBorderColor,
			String rightBorder, String rightBorderColor, String bottomBorder, String bottomBorderColor //, boolean overFlow	
//			, boolean isShownWhenCrosstab, boolean rowGroup, boolean columnGroup, CrosstabFunctionType crosstabFunctionType,
//			CrosstabGroupByType crosstabGroupByType, int crosstabCellWidth, 
			,String prefix, String postfix, boolean isShowInDetail,
			boolean isPrintWhenDetailOverFlow, String textFieldExpression, String printWhenExpression, boolean isPrintRepeatedValues,
			boolean isRemoveLineWhenBlank, boolean isBold, boolean isItalic, boolean isUnderLine, boolean isStrikeThrough,
			String style, String foreColor, HorizontalAlignment whenHeaderHAlignment
			) {
						
				this.attributeName = attributeName;
				this.x = x;
				this.y = y;
				this.width = width;
				
				this.height = height;
				this.key = key;
				this.label = StringUtils.isEmpty(label)?com.softtech.kismiss.utils.StringUtils.getLabel(attributeName):label;
				this.column = column;
				this.overFlow = isOverFlow;
				
				this.isBlankWhenNull = isBlankWhenNull;
				this.evaluationTime = evaluationTime;
				this.hyperlinkType = hyperlinkType;
				this.hyperlinkTarget = hyperlinkTarget;
				this.fontSize = fontSize;
				
				this.font = font;
				this.bgColor = bgColor;
				this.colorMode = StringUtils.isEmpty(bgColor)?colorMode: ColorMode.Opaque;
				this.leftPadding = leftPadding;
				this.rightPadding = rightPadding;
				
				this.topPadding = topPadding;
				this.bottomPadding = bottomPadding;
				this.lineWidth = lineWidth;
				this.lineStyle = lineStyle;
				this.horizontalAlignment = horizontalAlignment;
				
				this.verticalAlignment = verticalAlignment;
				this.columnHierarchy = columnHierarchy;
				this.widthPortion = widthPortion;
				this.heightPortion = heightPortion;
				this.pattern = pattern;
				
				this.type = type;
				this.recordNumber = recordNumber;
				this.resetWhenGrouped = isResetWhenGrouped;
				this.resetGroupName = resetGroupName;
				
				this.border = border;
				this.borderColor = borderColor;
				this.padding = padding;
				this.topBorder = topBorder;
				this.topBorderColor = topBorderColor;
				
				this.leftBorderColor = leftBorderColor;
				this.rightBorder = rightBorder;
				this.rightBorderColor = rightBorderColor;
				this.bottomBorder = bottomBorder;
				this.bottomBorderColor = bottomBorderColor;
				
//				this.isShownWhenCrosstab = isShownWhenCrosstab;
//				this.rowGroup = rowGroup;
//				this.columnGroup = columnGroup;
//				this.crosstabFunction = crosstabFunctionType;
//				this.crosstabGroupBy = crosstabGroupByType;
//				
//				this.crosstabCellWidth= crosstabCellWidth;
				this.prefix = prefix;
				this.postfix = postfix;
				this.isShowInDetail = isShowInDetail;
				this.isPrintWhenDetailOverFlow = isPrintWhenDetailOverFlow;
				
				this.textFieldExpression = textFieldExpression;
				this.printWhenExpression =  printWhenExpression;
				this.isPrintRepeatedValues = isPrintRepeatedValues;
				this.isRemoveLineWhenBlank = isRemoveLineWhenBlank;
				this.isBold = isBold;
				this.isItalic = isItalic;
				this.isUnderLine = isUnderLine;
				this.isStrikeThrough = isStrikeThrough;
				
				this.style = style;
				this.foreColor = foreColor;
				this.whenHeaderHAlignment = whenHeaderHAlignment;
	}
	
	public PropertyInfo(
			String attributeName, int x, int y, int width,
			int height, String key, String label, int column,
			boolean isOverFlow, boolean isBlankWhenNull, String evaluationTime,
			String hyperlinkType, String hyperlinkTarget, int fontSize,
			String font, String bgColor, ColorMode colorMode, int leftPadding, int rightPadding, int topPadding,
			int bottomPadding, double lineWidth, String lineStyle, HorizontalAlignment horizontalAlignment,
			VerticalAlignment verticalAlignment,
			String[] columnHierarchy, int widthPortion, int[] heightPortion,
			String pattern, String type, boolean recordNumber, String border, String borderColor, int padding,
			String topBorder, String topBorderColor, String leftBorder, String leftBorderColor,
			String rightBorder, String rightBorderColor, String bottomBorder, String bottomBorderColor, 
			HorizontalAlignment whenHeaderHAlignment //, boolean overFlow			
			) {
				this.attributeName = attributeName;
				this.x = x;
				this.y = y;
				this.width = width;
				
				this.height = height;
				this.key = key;
				this.label = StringUtils.isEmpty(label)?com.softtech.kismiss.utils.StringUtils.getLabel(attributeName):label;
				this.column = column;
				this.overFlow = isOverFlow;
				
				this.isBlankWhenNull = isBlankWhenNull;
				this.evaluationTime = evaluationTime;
				this.hyperlinkType = hyperlinkType;
				this.hyperlinkTarget = hyperlinkTarget;
				this.fontSize = fontSize;
				
				this.font = font;
				this.bgColor = bgColor;
				this.colorMode = StringUtils.isEmpty(bgColor)?colorMode: ColorMode.Opaque;
				this.leftPadding = leftPadding;
				this.rightPadding = rightPadding;
				
				this.topPadding = topPadding;
				this.bottomPadding = bottomPadding;
				this.lineWidth = lineWidth;
				this.lineStyle = lineStyle;
				this.horizontalAlignment = horizontalAlignment;
				
				this.verticalAlignment = verticalAlignment;
				this.columnHierarchy = columnHierarchy;
				this.widthPortion = widthPortion;
				this.heightPortion = heightPortion;
				this.pattern = pattern;
				
				this.type = type;
				this.recordNumber = recordNumber;
				
				this.border = border;
				this.borderColor = borderColor;
				this.padding = padding;
				this.topBorder = topBorder;
				this.topBorderColor = topBorderColor;
				this.leftBorderColor = leftBorderColor;
				this.rightBorder = rightBorder;
				this.rightBorderColor = rightBorderColor;
				this.bottomBorder = bottomBorder;
				this.bottomBorderColor = bottomBorderColor;
				this.whenHeaderHAlignment = whenHeaderHAlignment;
	}
	
	public PropertyInfo(){}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public boolean isOverFlow() {
		return overFlow;
	}

	public void setOverFlow(boolean overFlow) {
		this.overFlow = overFlow;
	}

	public boolean isBlankWhenNull() {
		return isBlankWhenNull;
	}

	public void setBlankWhenNull(boolean isBlankWhenNull) {
		this.isBlankWhenNull = isBlankWhenNull;
	}

	public String getEvaluationTime() {
		return evaluationTime;
	}

	public void setEvaluationTime(String evaluationTime) {
		this.evaluationTime = evaluationTime;
	}

	public String getHyperlinkType() {
		return hyperlinkType;
	}

	public void setHyperlinkType(String hyperlinkType) {
		this.hyperlinkType = hyperlinkType;
	}

	public String getHyperlinkTarget() {
		return hyperlinkTarget;
	}

	public void setHyperlinkTarget(String hyperlinkTarget) {
		this.hyperlinkTarget = hyperlinkTarget;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
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

	public int getTopPadding() {
		return topPadding;
	}

	public void setTopPadding(int topPadding) {
		this.topPadding = topPadding;
	}

	public int getBottomPadding() {
		return bottomPadding;
	}

	public void setBottomPadding(int bottomPadding) {
		this.bottomPadding = bottomPadding;
	}

	public double getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(double lineWidth) {
		this.lineWidth = lineWidth;
	}

	public String getLineStyle() {
		return lineStyle;
	}

	public void setLineStyle(String lineStyle) {
		this.lineStyle = lineStyle;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public HorizontalAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}

	public VerticalAlignment getVerticalAlignment() {
		return verticalAlignment;
	}

	public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getColumnHierarchy() {
		return columnHierarchy;
	}

	public void setColumnHierarchy(String[] columnHierarchy) {
		this.columnHierarchy = columnHierarchy;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public ColorMode getColorMode() {
		return colorMode;
	}

	public void setColorMode(ColorMode colorMode) {
		this.colorMode = colorMode;
	}

	public int getWidthPortion() {
		return widthPortion;
	}

	public void setWidthPortion(int widthPortion) {
		this.widthPortion = widthPortion;
	}

	public int[] getHeightPortion() {
		return heightPortion;
	}

	public void setHeightPortion(int[] heightPortion) {
		this.heightPortion = heightPortion;
	}

	public boolean isRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(boolean recordNumber) {
		this.recordNumber = recordNumber;
	}

	public boolean isResetWhenGrouped() {
		return resetWhenGrouped;
	}


	public void setResetWhenGrouped(boolean resetWhenGrouped) {
		this.resetWhenGrouped = resetWhenGrouped;
	}

	public String getResetGroupName() {
		return resetGroupName;
	}


	public void setResetGroupName(String resetGroupName) {
		this.resetGroupName = resetGroupName;
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

	public boolean isShownWhenCrosstab() {
		return isShownWhenCrosstab;
	}

	public void setShownWhenCrosstab(boolean isShownWhenCrosstab) {
		this.isShownWhenCrosstab = isShownWhenCrosstab;
	}

	public boolean isRowGroup() {
		return rowGroup;
	}

	public void setRowGroup(boolean rowGroup) {
		this.rowGroup = rowGroup;
	}

	public boolean isColumnGroup() {
		return columnGroup;
	}

	public void setColumnGroup(boolean columnGroup) {
		this.columnGroup = columnGroup;
	}

	public CrosstabFunctionType getCrosstabFunction() {
		return crosstabFunction;
	}

	public void setCrosstabFunction(CrosstabFunctionType crosstabFunction) {
		this.crosstabFunction = crosstabFunction;
	}

	public CrosstabGroupByType getCrosstabGroupBy() {
		return crosstabGroupBy;
	}

	public void setCrosstabGroupBy(CrosstabGroupByType crosstabGroupBy) {
		this.crosstabGroupBy = crosstabGroupBy;
	}

	public int getCrosstabCellWidth() {
		return crosstabCellWidth;
	}

	public void setCrosstabCellWidth(int crosstabCellWidth) {
		this.crosstabCellWidth = crosstabCellWidth;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getPostfix() {
		return postfix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}

	public boolean isShowInDetail() {
		return isShowInDetail;
	}

	public void setShowInDetail(boolean isShowInDetail) {
		this.isShowInDetail = isShowInDetail;
	}

	public boolean isPrintWhenDetailOverFlow() {
		return isPrintWhenDetailOverFlow;
	}

	public void setPrintWhenDetailOverFlow(boolean isPrintWhenDetailOverFlow) {
		this.isPrintWhenDetailOverFlow = isPrintWhenDetailOverFlow;
	}

	public String getTextFieldExpression() {
		return textFieldExpression;
	}

	public void setTextFieldExpression(String textFieldExpression) {
		this.textFieldExpression = textFieldExpression;
	}

	public String getPrintWhenExpression() {
		return printWhenExpression;
	}

	public void setPrintWhenExpression(String printWhenExpression) {
		this.printWhenExpression = printWhenExpression;
	}

	public boolean isPrintRepeatedValues() {
		return isPrintRepeatedValues;
	}

	public void setPrintRepeatedValues(boolean isPrintRepeatedValues) {
		this.isPrintRepeatedValues = isPrintRepeatedValues;
	}

	public boolean isRemoveLineWhenBlank() {
		return isRemoveLineWhenBlank;
	}

	public void setRemoveLineWhenBlank(boolean isRemoveLineWhenBlank) {
		this.isRemoveLineWhenBlank = isRemoveLineWhenBlank;
	}

	public boolean isBold() {
		return isBold;
	}

	public void setBold(boolean isBold) {
		this.isBold = isBold;
	}

	public boolean isUnderLine() {
		return isUnderLine;
	}

	public void setUnderLine(boolean isUnderLine) {
		this.isUnderLine = isUnderLine;
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

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getForeColor() {
		return foreColor;
	}

	public void setForeColor(String foreColor) {
		this.foreColor = foreColor;
	}

	public HorizontalAlignment getWhenHeaderHAlignment() {
		return whenHeaderHAlignment;
	}

	public void setWhenHeaderHAlignment(HorizontalAlignment whenHeaderHAlignment) {
		this.whenHeaderHAlignment = whenHeaderHAlignment;
	}

}
