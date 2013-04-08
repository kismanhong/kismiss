package com.softtech.kismiss.main;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import com.softtech.kismiss.constant.ReportConstant;
import com.softtech.kismiss.constant.ReportFactory;
import com.softtech.kismiss.enumer.PaperType;
import com.softtech.kismiss.property.Header;
import com.softtech.kismiss.property.Kismiss;

public abstract class KismissReportCore {
	/**
	 * @param clazz
	 * @param reportParams
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * Setting the report header all params
	 */
	@SuppressWarnings("rawtypes")
	protected Kismiss setReportParams(Class clazz,
			Map<String, Object> reportParams) throws IllegalArgumentException,
			IllegalAccessException {
		Kismiss kismiss = null;
		Annotation[] annotations = clazz.getAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation instanceof Kismiss) {
				kismiss = (Kismiss) annotation;
				setreportParams(kismiss, reportParams);
				setreportParams(reportParams, kismiss.paperType());				
			}
		}
		return kismiss;
	}
	
	/**
	 * @param kismiss
	 * @param reportParams
	 * @throws IllegalArgumentException
	 *             setting information of report header
	 */
	protected void setreportParams(Kismiss kismiss,
			Map<String, Object> reportParams) throws IllegalArgumentException {
		/*
		 * this should have a more reliable code, don't put one by one, so ugly
		 * to look @Hong
		 */
		Method[] methods = kismiss.getClass().getMethods();
		for (Method method : methods) {
			System.out.println("kismiss name :" + method.getName());
		}

		Field[] fields = kismiss.getClass().getFields();
		for (Field field : fields) {
			System.out.println("field value :" + field.getType());
		}

		reportParams.put(ReportFactory.REPORT_NAME, kismiss.name());
		reportParams.put(ReportFactory.PRINT_ORDER, kismiss.printOrder());
		reportParams.put(ReportFactory.ORIENTATION, kismiss.orientation());
		reportParams.put(ReportFactory.WHEN_NO_DATA_TYPE, kismiss.whenNoDataType());
		reportParams.put(ReportFactory.IS_FLOAT_COLUMN_FOOTER, kismiss.isFloatColumnFooter());		
		reportParams.put(ReportFactory.IS_TITLE_NEW_PAGE, kismiss.isTitleNewPage());
		reportParams.put(ReportFactory.IS_TITLE_EVERY_PAGE, kismiss.isTitleEveryPage());
//		reportParams.put(ReportFactory.IS_COLUMN_HEADER_EVERY_PAGE, kismiss.isColumnHeaderEveryPage());
		reportParams.put(ReportFactory.IS_SUMMARY_NEW_PAGE, kismiss.isSummaryNewPage());
		
		reportParams.put(ReportFactory.LEFT_MARGIN, kismiss.leftMargin());
		reportParams.put(ReportFactory.RIGHT_MARGIN, kismiss.rightMargin());
		reportParams.put(ReportFactory.TOP_MARGIN, kismiss.topMargin());
		reportParams.put(ReportFactory.BOTTOM_MARGIN, kismiss.bottomMargin());
		
	}

	/**
	 * @param reportParams
	 * @param paperType
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 *             set report header based on paper type
	 */
	protected void setreportParams(Map<String, Object> reportParams,
			PaperType paperType) throws IllegalArgumentException,
			IllegalAccessException {
		setReportParams(paperType, reportParams);
	}

	/**
	 * @param paperType
	 * @param reportParams
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 *             Switching paper type to get the paper information such as height and width of the paper
	 *             now, this can only support for A4 size
	 */
	@SuppressWarnings("rawtypes")
	protected void setReportParams(PaperType paperType,
			Map<String, Object> reportParams) throws IllegalArgumentException,
			IllegalAccessException {
		Class clazz = null;
		if (paperType == PaperType.A0) {
			clazz = ReportConstant.Paper_A0.class;
		} else if (paperType == PaperType.Custom) {
			clazz = ReportConstant.Paper_Custom.class;
		} else if (paperType == PaperType.Letter) {
			clazz = ReportConstant.Paper_Letter.class;
		} else if (paperType == PaperType.Note) {
			clazz = ReportConstant.Paper_Note.class;
		} else if (paperType == PaperType.Legal) {
			clazz = ReportConstant.Paper_Legal.class;
		} else if (paperType == PaperType.A1) {
			clazz = ReportConstant.Paper_A1.class;
		} else if (paperType == PaperType.A2) {
			clazz = ReportConstant.Paper_A2.class;
		} else if (paperType == PaperType.A3) {
			clazz = ReportConstant.Paper_A3.class;
		} else if (paperType == PaperType.A4) {
			clazz = ReportConstant.Paper_A4.class;
		} else if (paperType == PaperType.A5) {
			clazz = ReportConstant.Paper_A5.class;
		} else if (paperType == PaperType.A6) {
			clazz = ReportConstant.Paper_A6.class;
		} else if (paperType == PaperType.A7) {
			clazz = ReportConstant.Paper_A7.class;
		} else if (paperType == PaperType.A8) {
			clazz = ReportConstant.Paper_A8.class;
		} else if (paperType == PaperType.A8) {
			clazz = ReportConstant.Paper_A8.class;
		} else if (paperType == PaperType.A10) {
			clazz = ReportConstant.Paper_A10.class;
		} else if (paperType == PaperType.B0) {
			clazz = ReportConstant.Paper_B0.class;
		} else if (paperType == PaperType.B1) {
			clazz = ReportConstant.Paper_B1.class;
		} else if (paperType == PaperType.B2) {
			clazz = ReportConstant.Paper_B2.class;
		} else if (paperType == PaperType.B3) {
			clazz = ReportConstant.Paper_B3.class;
		} else if (paperType == PaperType.B4) {
			clazz = ReportConstant.Paper_B4.class;
		} else if (paperType == PaperType.B5) {
			clazz = ReportConstant.Paper_B5.class;
		}
		//setting paper property to header params
		Field[] fields = clazz.getFields();
		for (Field field : fields) {
			if(ReportFactory.COLUMN_WIDTH.equals(field.getName()))
				reportParams.put(field.getName(), (Integer) field.get(field.getName()) - (Integer) reportParams.get(ReportFactory.LEFT_MARGIN) + 28);
			else
				reportParams.put(field.getName(), field.get(field.getName()));
		}
	}
	
	/**
	 * @param clazz
	 * @param headerParams
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * put the band information
	 */
	@SuppressWarnings("rawtypes")
	protected Header setHeaderParams(Class clazz,
			Map<String, Object> headerParams) throws IllegalArgumentException,
			IllegalAccessException {
		Header band = null;
		Annotation[] annotations = clazz.getAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation instanceof Header) {
				band = (Header) annotation;	
				setHeaderParams(band, headerParams);
			}
		}
		return band;
	}
	
	/**
	 * @param band
	 * @param headerParams
	 * @throws IllegalArgumentException
	 * called by Band setParams
	 */
	protected void setHeaderParams(Header band,
			Map<String, Object> headerParams) throws IllegalArgumentException {	
//		headerParams.put(ReportFactory.DETAIL_HEIGHT, band.detailHeight());
		headerParams.put(ReportFactory.COLUMN_HEADER_HEIGHT, band.columnHeaderHeight());
		headerParams.put(ReportFactory.COLUMN_HEADER_BOLD, band.isColumnHeaderBold());
		headerParams.put(ReportFactory.COLUMN_HEADER_FONT_NAME, band.columnHeaderFontName());
		headerParams.put(ReportFactory.COLUMN_HEADER_FONT_SIZE, band.columnHeaderFontSize());

	}
	
	
}
