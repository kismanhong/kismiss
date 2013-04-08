package com.softtech.kismiss.property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.softtech.kismiss.constant.Encoding;
import com.softtech.kismiss.constant.ReportConstant;
import com.softtech.kismiss.enumer.HorizontalAlignment;
import com.softtech.kismiss.enumer.Orientation;
import com.softtech.kismiss.enumer.PaperType;
import com.softtech.kismiss.enumer.PrintOrder;
import com.softtech.kismiss.enumer.ResourceMissingType;
import com.softtech.kismiss.enumer.WhenNoDataFoundType;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * Kismiss.java used for getting the report main information
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Kismiss {
	
	/**
	 * @return paperType
	 */
	public PaperType paperType() default PaperType.A4;
	/**
	 * @return String
	 * Report Name
	 */
	public String name();
	/**
	 * @return Enum PaperType, see @ class PaperType
	 * Type of paper for size purpose
	 */
//	public PaperType type() default PaperType.A4;
	/**
	 * @return Enum PrintOrder, see @ class PrintOrder
	 * Vetical or Horinzontal Print
	 */
	public PrintOrder printOrder() default PrintOrder.Vertical;
	/**
	 * @return Enum Orientation, see @ class Orientation
	 * Portrait or Landscape
	 */
	public Orientation orientation() default Orientation.Portrait;
	
	/**
	 * @return int
	 * Report detail section height, see Jasper Report documentation
	 */
//	public int detailHeight() default ReportConstant.Detail_Height;

	/**
	 * @return int
	 * Report Page width
	 */
	public int pageWidth() default -1;
	/**
	 * @return int
	 * Report Page height
	 */
	public int pageHeight() default -1;
	/**
	 * @return int 
	 * Report Column width (all width)
	 */
	public int columnWidth() default -1;
	/**
	 * @return int
	 * Report Column Spacing
	 */
	public int columnSpacing() default -1;

	/**
	 * @return int
	 * Report Left Margin
	 */
	public int leftMargin() default ReportConstant.Paper.Left_Margin;
	/**
	 * @return int
	 * Report Right Margin
	 */
	public int rightMargin() default ReportConstant.Paper.Right_Margin;
	/**
	 * @return int
	 * Report Top Margin
	 */
	public int topMargin() default ReportConstant.Paper.Top_Margin;
	/**
	 * @return int
	 * Report Bottom Margin
	 */
	public int bottomMargin() default ReportConstant.Paper.Bottom_Margin;

	/**
	 * @return String
	 * Report when no data section, see Jasper documentation
	 */
	public WhenNoDataFoundType whenNoDataType() default WhenNoDataFoundType.AllSectionsNoDetail;
	/**
	 * @return boolean
	 * Report float column, see Jasper docs
	 */
	public boolean isFloatColumnFooter() default true;
	/**
	 * @return boolean
	 * Report Title for the new page, show or not
	 */
	public boolean isTitleNewPage() default false;
	
	/**
	 * @return
	 */
	public boolean isTitleEveryPage() default true;
	
	/**
	 * @return
	 */
//	public boolean isColumnHeaderEveryPage() default false;
	/**
	 * @return boolean
	 * Report summary for every page, show or not
	 */
	public boolean isSummaryNewPage() default false;

	/**
	 * @return String
	 * Report encoding
	 */
	public String encoding() default Encoding.UTF_8;
	/**
	 * @return boolean
	 * Report column auto width
	 */
	public boolean columnAutoSize() default false;
	
	/**
	 * @return boolean
	 */
	public boolean isIgnorePagination() default false;
	
	/**
	 * @return ResourceMissingType
	 */
	public ResourceMissingType whenResourceMissingType() default ResourceMissingType.Null;
	
	/**
	 * @return
	 */
//	public boolean isCrosstab() default false;
	
	/**
	 * @return
	 */
//	public int crosstabListHeaderWidth() default 100;
	
	/**
	 * @return
	 */
	public int titleFontSize() default 10;
	
	/**
	 * @return
	 */
	public boolean titleIsBold() default false;
	
	/**
	 * @return
	 */
	public String titlePdfFontName() default "Helvetica-Bold";
	
	/**
	 * @return
	 */
	public HorizontalAlignment titleAlignment() default HorizontalAlignment.Center;
	
	public double lineWidth() default 0;
	
	public HorizontalAlignment pageNumberAlignment() default HorizontalAlignment.Right;
	
	public String noDataFound() default "No Data Found";
	
	public int titleHeight() default 25;
	
	public boolean isRepeatColumnHeaderXls() default true;
	
	public boolean isIgnorePaginationXls() default true;
	
//	public boolean isCache() default false;
	
}
