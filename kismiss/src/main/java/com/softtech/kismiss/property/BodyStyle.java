package com.softtech.kismiss.property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author kismanhong
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BodyStyle {
	public abstract String name() default "";
	public abstract boolean isDefault() default false;
	public abstract String backColor() default "";
	public abstract boolean isStyledText() default false;
}
