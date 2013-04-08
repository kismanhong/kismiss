package com.softtech.kismiss.property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * Property is used for setting information for field
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

public abstract @interface FieldGroup{
//	public abstract String key() default "";
	public abstract String[] label() default "";
	public abstract boolean isIncluded() default true;
}
