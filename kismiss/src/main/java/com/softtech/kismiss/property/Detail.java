package com.softtech.kismiss.property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.softtech.kismiss.constant.ReportConstant;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public abstract @interface Detail {
	public String oddEvenColor() default "";
	public double lineWidth() default 0;
	public abstract int height() default ReportConstant.Detail_Height;
}
