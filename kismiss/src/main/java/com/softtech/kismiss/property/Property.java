package com.softtech.kismiss.property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.softtech.kismiss.enumer.ColorMode;
import com.softtech.kismiss.enumer.EvaluationTimeType;
import com.softtech.kismiss.enumer.FontType;
import com.softtech.kismiss.enumer.HiperlinkTargetType;
import com.softtech.kismiss.enumer.HorizontalAlignment;
import com.softtech.kismiss.enumer.LineStyle;
import com.softtech.kismiss.enumer.VerticalAlignment;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * Property is used for setting information for field
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public abstract @interface Property{
	public abstract String name() default "";
	public abstract boolean[] isStretchWithOverflow() default true;
	public abstract boolean[] isBlankWhenNull() default true;
	public abstract EvaluationTimeType evaluationTime() default EvaluationTimeType.Now;
	public abstract String hyperlinkType() default "None";
	public abstract HiperlinkTargetType hyperlinkTarget() default HiperlinkTargetType.Self;
	public abstract int[] width();
	public abstract int height() default 0;
	public abstract int[] fontSize() default 8;
	public abstract FontType[] font() default FontType.Arial;
	public abstract String[] foreColor() default "";
	
	public abstract double lineWidth() default -1;
	public abstract LineStyle lineStyle() default LineStyle.Solid;
	public abstract String[] pattern() default "";
	public abstract String key() default "";
	public abstract int[] position();
	public abstract boolean[] isInclude() default true;
	public abstract String label() default "";
	public abstract VerticalAlignment[] verticalAlignment() default VerticalAlignment.Top;
	public abstract HorizontalAlignment[] horizontalAlignment() default HorizontalAlignment.Left;
	public abstract String[] columnHierarchy() default {};
	public abstract String[] bgColor() default "";
	public abstract ColorMode[] colorMode() default ColorMode.Transparent;
	public abstract int widthPortion() default 0;
	public abstract int[] heightPortion() default 0;
//	public abstract boolean isRecordNumber() default false;
	public abstract String evaluationGroup() default "";
	public abstract String bookmarkLevel() default "0";
	public abstract String[] textFieldExpression() default "";
	public abstract boolean[] isPrintWhenDetailOverFlow() default true;
	public abstract String[] printWhenExpression() default "";
	public abstract boolean[] isPrintRepeatedValues() default true;
	public abstract boolean[] isRemoveLineWhenBlank() default false;
	
	public abstract boolean[] isBold() default false;
	public abstract boolean[] isUnderline() default false;
	public abstract boolean[] isItalic() default false;
	public abstract boolean[] isStrikeThrough() default false;
	
	/* box section */
	public abstract String border() default "";
	public abstract String borderColor() default "";
	public abstract int[] padding() default 0;
	public abstract String topBorder() default "";
	public abstract String topBoderColor() default "";
	public abstract int[] leftPadding() default 3;
	public abstract String leftBorder() default "";
	public abstract String leftBorderColor() default "";
	public abstract int[] rightPadding() default 3;
	public abstract String rightBorder() default "";
	public abstract String rightBorderColor() default "";
	public abstract int[] topPadding() default 3;
	public abstract String bottomBorder() default "";
	public abstract String bottomBorderColor() default "";
	public abstract int[] bottomPadding() default 3;
	
	/* crosstab */
//	public abstract boolean isShownWhenCrosstab() default false;
//	public abstract boolean rowGroup() default false;
//	public abstract boolean columnGroup() default false;
//	public abstract CrosstabFunctionType crosstabFunction() default CrosstabFunctionType.Nothing;
//	public abstract CrosstabGroupByType crosstabGroupBy() default CrosstabGroupByType.Nothing;
//	public abstract int crosstabCellWidth() default 50;

	public abstract String[] innerProperty() default {};
	public abstract String[] prefix() default "";
	public abstract String[] postfix() default "";
	public abstract boolean[] isShowInDetail() default true;
	public abstract String[] style() default "";
	
	public abstract HorizontalAlignment whenHeaderHAlignment() default HorizontalAlignment.None;
}
