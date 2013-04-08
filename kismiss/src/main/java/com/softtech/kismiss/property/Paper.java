package com.softtech.kismiss.property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.softtech.kismiss.enumer.PaperType;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * Paper is used for setting information report name and paper type
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Paper {
	public String name() default "Kismiss";
	public PaperType type() default PaperType.A4;
}
