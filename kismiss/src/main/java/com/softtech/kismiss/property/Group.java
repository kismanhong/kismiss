package com.softtech.kismiss.property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.softtech.kismiss.enumer.CalculationPrintType;
import com.softtech.kismiss.enumer.FontType;
import com.softtech.kismiss.enumer.HorizontalAlignment;
import com.softtech.kismiss.enumer.LineStyle;
import com.softtech.kismiss.enumer.VerticalAlignment;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * Group is used for setting information Group
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public abstract @interface Group {
	public abstract String[] calculation() default {};
	public abstract int bandHeight() default 20;
	public abstract int x() default 0;
	public abstract int y() default 0;
	public abstract int height() default 20;
	public abstract String groupBy();
	public abstract int fontSize() default 8;
	public abstract FontType font() default FontType.Arial;
	public abstract boolean isBold() default true;
	public abstract int leftPadding() default 10;
	public abstract int rightPadding() default 10;
	public abstract double lineWidth() default -1;
	public abstract LineStyle lineStyle() default LineStyle.Solid;
	public abstract VerticalAlignment verticalAlignment() default VerticalAlignment.Middle;
	public abstract HorizontalAlignment horizontalAlignment() default HorizontalAlignment.Left;
	public abstract HorizontalAlignment labelHorizontalAlignment() default HorizontalAlignment.Center;
	public abstract CalculationPrintType calculationPrintType() default CalculationPrintType.GroupExpression;
	public abstract String backColor() default "";
	public abstract String groupLabel() default "";
	public abstract String labelPrefix() default "";
	public abstract String labelSuffix() default "";
}
