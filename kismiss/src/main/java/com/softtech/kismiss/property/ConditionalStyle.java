package com.softtech.kismiss.property;

import com.softtech.kismiss.constant.BorderConstant;
import com.softtech.kismiss.enumer.ColorMode;
import com.softtech.kismiss.enumer.FontType;
import com.softtech.kismiss.enumer.HorizontalAlignment;
import com.softtech.kismiss.enumer.LineSpace;
import com.softtech.kismiss.enumer.Rotation;
import com.softtech.kismiss.enumer.ScaleImageType;
import com.softtech.kismiss.enumer.VerticalAlignment;

/**
 * @author kismanhong
 * used for declaring property of conditional style
 */
public @interface ConditionalStyle {
//	public abstract String name() default "";
	public abstract boolean isDefault() default false;
//	public abstract BodyStyle style();
	public abstract ColorMode mode() default ColorMode.Transparent;
	public abstract String foreColor() default "";
	public abstract String backColor() default "";
	public abstract String pen() default BorderConstant.NONE;
	public abstract String fill() default "";
	public abstract String radius() default "";
	public abstract ScaleImageType scaleImage() default ScaleImageType.None;
	public abstract HorizontalAlignment hAlign() default HorizontalAlignment.Left;
	public abstract VerticalAlignment vAlign() default VerticalAlignment.Top;
	public abstract String border() default BorderConstant.NONE;
	public abstract String borderColor() default "";
	public abstract int padding() default 3;
	public abstract String topBorder() default BorderConstant.NONE;
	public abstract String topBorderColor() default "";
	public abstract int topPadding() default -1;
	public abstract String leftBorder() default BorderConstant.NONE;
	public abstract String leftBorderColor() default "";
	public abstract int leftPadding() default -1;
	public abstract String bottomBorder() default BorderConstant.NONE;
	public abstract String bottomBorderColor() default "";
	public abstract int bottomPadding() default -1;
	public abstract String rightBorder() default BorderConstant.NONE;
	public abstract String rightBorderColor() default "";
	public abstract int rightPadding() default -1;
	public abstract Rotation rotation() default Rotation.None;
	public abstract LineSpace lineSpace() default LineSpace.None;
	public abstract boolean isStyledText() default false;
	public abstract FontType fontName() default FontType.None;
	public abstract int fontSize() default -1;
	public abstract boolean isBold() default false;
	public abstract boolean isUnderline() default false;
	public abstract boolean isItalic() default false;
	public abstract boolean isStrikeThrough() default false;
	public abstract FontType pdfFontName() default FontType.None;
	public abstract String pdfEncoding() default "";
	public abstract boolean isPdfEmbedded() default false;
	public abstract String pattern() default "";
	public abstract boolean isBlankWhenNull() default false;
	public abstract String conditionalExpression() default "";
	
	public abstract double topLineWidth() default 0.5;
	public abstract double leftLineWidth() default 0.5;
	public abstract double bottomLineWidth() default 0.5;
	public abstract double rightLineWidth() default 0.5;
	public abstract String lineColor() default "";
	public abstract double lineWidth() default -1;
	
	public abstract String topLineColor() default "";
	public abstract String leftLineColor() default "";
	public abstract String bottomLineColor() default "";
	public abstract String rightLineColor() default "";
}
