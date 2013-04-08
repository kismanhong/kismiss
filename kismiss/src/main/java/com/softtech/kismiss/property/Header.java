package com.softtech.kismiss.property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.softtech.kismiss.constant.ReportConstant;
import com.softtech.kismiss.enumer.FontType;
import com.softtech.kismiss.enumer.HorizontalAlignment;
import com.softtech.kismiss.enumer.LineStyle;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * Header is used for setting information of report column header
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public abstract @interface Header {
	public abstract int backgroundHeight() default 0;
	public abstract boolean backgroundIsSplitAllowed() default true;
//	public abstract int titleHeight() default ReportConstant.Title_Height;
	public abstract boolean titleIsSplitAllowed() default true;
//	public abstract int pageHeaderHeight() default 0;
//	public abstract boolean pageHeaderIsSplitAllowed() default true;
	public abstract int columnHeaderHeight() default ReportConstant.Column_Header_Height;
	public abstract boolean columnHeightIsSplitAllowed() default true;
//	public abstract int detailHeight() default ReportConstant.Detail_Height;
	public abstract boolean detailIsSplitAllowed() default true;
//	public abstract int columnFooterHeight() default 0;
//	public abstract boolean columnFooterIsSplitAllowed() default true;
//	public abstract int pageFooterHeight() default 0;
//	public abstract boolean pageFooterIsSplitAllowed() default true;
//	public abstract int summaryHeight() default 0;
//	public abstract boolean summaryIsSplitAllowed() default true;
	public int columnHeaderFontSize() default ReportConstant.Column_Header_Font_Size;
	public FontType columnHeaderFontName() default FontType.Arial;
	public boolean isColumnHeaderBold() default false;
	public String columnHeaderColor() default "";
//	public String oddEvenColor() default "";
	public double lineWidth() default -1;
	public double topLineWidth() default 0;
	public double leftLineWidth() default 0;
	public double bottomLineWidth() default 0;
	public double rightLineWidth() default 0;
	public LineStyle lineStyle() default LineStyle.Solid;
	public HorizontalAlignment textAlignment() default HorizontalAlignment.Center;
	
	public boolean isColumnHeaderEveryPage() default true;
}
