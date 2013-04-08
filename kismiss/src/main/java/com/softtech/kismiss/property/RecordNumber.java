package com.softtech.kismiss.property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * RecordNumber is used for record numbering
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public abstract @interface RecordNumber{
	public abstract boolean isResetWhenGrouped() default false;
	public abstract String resetGroupName() default "";
}
