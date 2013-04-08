package com.softtech.kismiss.property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.softtech.kismiss.enumer.CacheStrategyType;

/**
 * @author kismanhong
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Cache {
	public abstract CacheStrategyType cacheStrategyType() default CacheStrategyType.jasper;
	public abstract String relativePath() default "";
	public abstract String name();
}
