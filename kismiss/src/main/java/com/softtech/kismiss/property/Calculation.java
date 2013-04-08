package com.softtech.kismiss.property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.softtech.kismiss.enumer.CalculationType;
import com.softtech.kismiss.enumer.FontType;
import com.softtech.kismiss.enumer.HorizontalAlignment;
import com.softtech.kismiss.enumer.LineStyle;
import com.softtech.kismiss.enumer.VerticalAlignment;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * Calculation is used for setting information of calculation (Sum, Average, etc.)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Calculation {
	public abstract String attribute() default "kismiss";
	public abstract CalculationType[] calculationType();
	
	public abstract FontType fontType() default FontType.Arial;
	public abstract int fontSize() default 8;
	public abstract VerticalAlignment verticalAlignment() default VerticalAlignment.Middle;
	public abstract HorizontalAlignment horizontalAlignment() default HorizontalAlignment.Center;
	public abstract HorizontalAlignment labelHorizontalAlignment() default HorizontalAlignment.Center;
	public abstract boolean bold() default false;
	public abstract double lineWidth() default 0.5;
	public abstract LineStyle lineStyle() default LineStyle.Solid;
	public abstract String pattern() default "#,##0.00";
	public abstract String label() default "";
	public abstract String backColor() default "";
//	public abstract int x() default 0;
//	public abstract int y() default 0;
//	public abstract int height() default 20;
//	public abstract int width() default 20;
}
