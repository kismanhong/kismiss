package com.softtech.kismiss.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softtech.kismiss.comparator.XComparator;
import com.softtech.kismiss.constant.ReportConstant;
import com.softtech.kismiss.constant.ReportFactory;
import com.softtech.kismiss.enumer.ColorMode;
import com.softtech.kismiss.enumer.HorizontalAlignment;
import com.softtech.kismiss.enumer.Orientation;
import com.softtech.kismiss.enumer.PaperType;
import com.softtech.kismiss.enumer.VerticalAlignment;
import com.softtech.kismiss.exception.KismissException;
import com.softtech.kismiss.exception.ReportDataAccessException;
import com.softtech.kismiss.handler.ErrorCode;
import com.softtech.kismiss.handler.ErrorHandler;
import com.softtech.kismiss.model.CalculationInfo;
import com.softtech.kismiss.model.ConditionalStyleInfo;
import com.softtech.kismiss.model.GroupInfo;
import com.softtech.kismiss.model.PropertyInfo;
import com.softtech.kismiss.model.StyleInfo;
import com.softtech.kismiss.property.ConditionalStyle;
import com.softtech.kismiss.property.ConditionalStyles;
import com.softtech.kismiss.property.Detail;
import com.softtech.kismiss.property.Header;
import com.softtech.kismiss.property.Kismiss;
import com.softtech.kismiss.property.Property;
import com.softtech.kismiss.property.Style;
import com.softtech.kismiss.property.Styles;
import com.softtech.kismiss.utils.ArrayUtilz;
import com.softtech.kismiss.utils.MethodUtils;

/**
 * @author kismanhong
 * putting params using this class
 */
public abstract class KismissMiddleCore extends KismissCore {
	
	/**
	 * declare logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(KismissMiddleCore.class);
	
	/**
	 * @param clazz
	 * @param reportParams
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * Setting the report header all params
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("rawtypes")
	protected Kismiss setReportParams(Class clazz,
			Map<String, Object> reportParams) throws Exception {
		Kismiss kismiss = null;
		try {
			Annotation[] annotations = clazz.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof Kismiss) {
					kismiss = (Kismiss) annotation;
					setReportParams(kismiss, reportParams);
					setReportParams(reportParams, kismiss, kismiss.paperType());				
				}
			}
		} catch (Exception e) {
			logger.error("[Kismiss:setreportParams] Error happended => {}", e.getMessage());
			throw new Exception(e);
		}
		return kismiss;
	}
	
	/**
	 * @param reportParams
	 * @param paperType
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 *             set report header based on paper type
	 * @throws ClassNotFoundException 
	 */
	private void setReportParams(Map<String, Object> reportParams, Kismiss kismiss,
			PaperType paperType) throws Exception {
		setReportParams(paperType, kismiss, reportParams);
		overideParamsWhenDefined(kismiss, reportParams);
	}
	
	/**
	 * @param paperType
	 * @param reportParams
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 *             Switching paper type to get the paper information such as height and width of the paper
	 *             now, this can only support for A4 size
	 * @throws ClassNotFoundException 
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 */
	@SuppressWarnings("rawtypes")
	private void setReportParams(PaperType paperType, Kismiss kismiss,
			Map<String, Object> reportParams) throws IllegalArgumentException,
			IllegalAccessException, ClassNotFoundException, SecurityException, NoSuchFieldException {
		Class clazz = ReportConstant.class.getClassLoader().loadClass(ReportConstant.class.getCanonicalName()+"$Paper_"+paperType.toString()); 

		//setting paper property to header params
		Field[] fields = clazz.getFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if(ReportFactory.COLUMN_WIDTH.equals(field.getName()) && kismiss.orientation() == Orientation.Portrait){		
				reportParams.put(field.getName(), (Integer) field.get(field.getName()) - kismiss.leftMargin() + kismiss.rightMargin());
			}
			else if(ReportFactory.COLUMN_WIDTH.equals(field.getName()) && kismiss.orientation() == Orientation.Landscape){
				reportParams.put(field.getName(), (Integer) clazz.getField(ReportFactory.PAGE_HEIGHT).get(ReportFactory.PAGE_HEIGHT)
						- (kismiss.leftMargin() + kismiss.rightMargin()));
			}
			else if(ReportFactory.PAGE_WIDTH.equals(field.getName()) && kismiss.orientation() == Orientation.Landscape){
				reportParams.put(ReportFactory.PAGE_HEIGHT, field.get(field.getName()));
			}
			else if(ReportFactory.PAGE_HEIGHT.equals(field.getName()) && kismiss.orientation() == Orientation.Landscape){
				reportParams.put(ReportFactory.PAGE_WIDTH, field.get(field.getName()));
			}
			else{
				reportParams.put(field.getName(), field.get(field.getName()));
			}
		}
	}
	
	/**
	 * @param kismiss
	 * @param reportParams
	 */
	private void overideParamsWhenDefined(Kismiss kismiss, Map<String, Object> reportParams){
		if(kismiss.pageWidth() > -1){
			reportParams.put(ReportFactory.PAGE_WIDTH, kismiss.pageWidth());
		}
		if(kismiss.pageHeight() > -1){
			reportParams.put(ReportFactory.PAGE_HEIGHT, kismiss.pageHeight());
		}
		if(kismiss.columnWidth() > -1){
			reportParams.put(ReportFactory.COLUMN_WIDTH, kismiss.columnWidth());
		}
		if(kismiss.columnSpacing() > -1){
			reportParams.put(ReportFactory.COLUMN_SPACING, kismiss.columnSpacing());
		}
	}
	
	/**
	 * @param kismiss
	 * @param reportParams
	 * @throws IllegalArgumentException
	 *             setting information of report header
	 */
	private void setReportParams(Kismiss kismiss,
			Map<String, Object> reportParams) throws KismissException {
		try {
			reportParams.put(ReportFactory.REPORT_NAME, kismiss.name());
			reportParams.put(ReportFactory.PRINT_ORDER, kismiss.printOrder());
			reportParams.put(ReportFactory.ORIENTATION, kismiss.orientation());
			reportParams.put(ReportFactory.WHEN_NO_DATA_TYPE, kismiss.whenNoDataType());
			reportParams.put(ReportFactory.IS_FLOAT_COLUMN_FOOTER, kismiss.isFloatColumnFooter());		
			
			reportParams.put(ReportFactory.IS_TITLE_NEW_PAGE, kismiss.isTitleNewPage());
			reportParams.put(ReportFactory.IS_TITLE_EVERY_PAGE, kismiss.isTitleEveryPage());
			reportParams.put(ReportFactory.IS_SUMMARY_NEW_PAGE, kismiss.isSummaryNewPage());
			reportParams.put(ReportFactory.IS_PAGINATION_IGNORE, kismiss.isIgnorePagination());
			
			reportParams.put(ReportFactory.LEFT_MARGIN, kismiss.leftMargin());
			reportParams.put(ReportFactory.RIGHT_MARGIN, kismiss.rightMargin());
			reportParams.put(ReportFactory.TOP_MARGIN, kismiss.topMargin());
			reportParams.put(ReportFactory.BOTTOM_MARGIN, kismiss.bottomMargin());
			
			reportParams.put(ReportFactory.TITLE_FONT_SIZE, kismiss.titleFontSize());
			reportParams.put(ReportFactory.TITLE_ALIGNMENT, kismiss.titleAlignment());
			reportParams.put(ReportFactory.TITLE_IS_BOLD, kismiss.titleIsBold());		
			reportParams.put(ReportFactory.TITLE_PDF_FONT_NAME, kismiss.titlePdfFontName());
			
			reportParams.put(ReportFactory.PAGE_NUMBER_ALIGNMENT, kismiss.pageNumberAlignment());
			reportParams.put(ReportFactory.NO_DATA_FOUND, kismiss.noDataFound());
			reportParams.put(ReportFactory.COLUMN_AUTO_SIZE, kismiss.columnAutoSize());
			reportParams.put(ReportFactory.TITLE_HEIGHT, kismiss.titleHeight());
			
			reportParams.put(ReportFactory.IS_REPEAT_COLUMN_HEADER_XLS, kismiss.isRepeatColumnHeaderXls());
			reportParams.put(ReportFactory.IS_IGNORE_PAGINATION_XLS, kismiss.isIgnorePaginationXls());
			
		} catch (Exception e) {
			logger.error("[Kismiss:setreportParams] Error happended => {}", e.getMessage());
			throw new KismissException(e);
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
	protected Header setHeaderParams(Class clazz, Map<String, Object> headerParams) throws Exception {
		Header header = null;
		try {
			Annotation[] annotations = clazz.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof Header) {
					header = (Header) annotation;	
					setHeaderParams(header, headerParams);
				}
			}
		} catch (Exception e) {
			logger.error("[Kismiss:setHeaderParams] Error happended => {}", e.getMessage());
			throw new Exception(e);
		}
		return header;
	}
	
	/**
	 * @param clazz
	 * @param detailParams
	 * @return
	 * @throws KismissException
	 */
	@SuppressWarnings("rawtypes")
	protected Detail setDetailParams(Class clazz, Map<String, Object> detailParams) throws KismissException {
		Detail detail = null;
		try {
			Annotation[] annotations = clazz.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof Detail) {
					detail = (Detail) annotation;	
					setDetailParams(detail, detailParams);
				}
			}
		} catch (Exception e) {
			logger.error("[Kismiss:setDetailParams] Error happended => {}", e.getMessage());
			throw new KismissException(e);
		}
		return detail;
	}
	
	/**
	 * @param band
	 * @param headerParams
	 * @throws IllegalArgumentException
	 * called by Header setParams
	 */
	private void setHeaderParams(Header header, Map<String, Object> headerParams) throws KismissException {	
		try {
			headerParams.put(ReportFactory.COLUMN_HEADER_HEIGHT, header.columnHeaderHeight());
			headerParams.put(ReportFactory.COLUMN_HEADER_BOLD, header.isColumnHeaderBold());
			headerParams.put(ReportFactory.COLUMN_HEADER_FONT_NAME, header.columnHeaderFontName());
			headerParams.put(ReportFactory.COLUMN_HEADER_FONT_SIZE, header.columnHeaderFontSize());
			headerParams.put(ReportFactory.HEADER_LINE_WIDTH, header.lineWidth());
			headerParams.put(ReportFactory.HEADER_LINE_STYLE, header.lineStyle());
			headerParams.put(ReportFactory.IS_HEADER_EVERY_PAGE, header.isColumnHeaderEveryPage());
			headerParams.put(ReportFactory.HEADER_LEFT_LINE_WIDTH, header.lineWidth() > -1 ? header.lineWidth() : header.leftLineWidth());
			headerParams.put(ReportFactory.HEADER_RIGHT_LINE_WIDTH, header.lineWidth() > -1 ? header.lineWidth() : header.rightLineWidth());
			headerParams.put(ReportFactory.HEADER_TOP_LINE_WIDTH, header.lineWidth() > -1 ? header.lineWidth() : header.topLineWidth());
			headerParams.put(ReportFactory.HEADER_BOTTOM_LINE_WIDTH, header.lineWidth() > -1 ? header.lineWidth() : header.bottomLineWidth());
//			headerParams.put(ReportFactory.TEXT_ALIGNMENT, header.textAlignment());
		} catch (Exception e) {
			logger.error("[Kismiss:setHeaderParams] Error happended => {}", e.getMessage());
			throw new KismissException(e);
		}
	}
	
	/**
	 * @param band
	 * @param headerParams
	 * @throws IllegalArgumentException
	 * called by Band setParams
	 */
	private void setDetailParams(Detail detail, Map<String, Object> detailParams) throws KismissException {	
		try {
			detailParams.put(ReportFactory.DETAIL_HEIGHT, detail.height());
			detailParams.put(ReportFactory.ODD_EVEN_COLOR, detail.oddEvenColor());
			detailParams.put(ReportFactory.HEADER_LINE_WIDTH, detail.lineWidth());
		} catch (Exception e) {
			logger.error("[Kismiss:setHeaderParams] Error happended => {}", e.getMessage());
			throw new KismissException(e);
		}
	}
	
	/**
	 * @param clazz
	 * @param styleInfos
	 * @throws KismissException
	 * putting style/s to collection
	 */
	@SuppressWarnings("rawtypes")
	protected void setStyles(Class clazz, List<StyleInfo> styleInfos) throws KismissException {
		try {
			Annotation[] annotations = clazz.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof Styles) {
					Styles styles = (Styles) annotation;
					Style[] styleses = styles.styles();
					for (Style style : styleses) {
						StyleInfo styleInfo = new StyleInfo(style.name(), style.isDefault(), style.mode(), style.foreColor(), style.backColor(), 
								style.pen(), style.fill(), style.radius(), style.scaleImage(), style.hAlign(), style.vAlign(), style.border(), 
								style.borderColor(), 0, style.topBorder(), style.topBorderColor(), style.topPadding(), 
								style.leftBorder(), style.leftBorderColor(), style.leftPadding(), style.bottomBorder(), style.bottomBorderColor(), 
								style.bottomPadding(), style.rightBorder(), style.rightBorderColor(), style.rightPadding(), style.rotation(),
								style.lineSpace(), style.isStyledText(), style.fontName(), style.fontSize(), style.isBold(), style.isUnderline(), 
								style.isItalic(), style.isStrikeThrough(), style.pdfFontName(), style.pdfEncoding(), style.isPdfEmbedded(), 
								style.pattern(), style.isBlankWhenNull());
						ConditionalStyles conditionalStyles2 = style.conditionalStyles();
						ConditionalStyle[] conditionalStyles3 = conditionalStyles2.conditionalStyles();
						ConditionalStyleInfo conditionalStyleInfo;
						List<ConditionalStyleInfo> conditionalStyleInfos = new ArrayList<ConditionalStyleInfo>();
						for (ConditionalStyle conditionalStyle : conditionalStyles3) {
							conditionalStyleInfo = new ConditionalStyleInfo(conditionalStyle.mode(), conditionalStyle.foreColor(), 
									conditionalStyle.backColor(), conditionalStyle.pen(), conditionalStyle.fill(),
									conditionalStyle.radius(), conditionalStyle.scaleImage(), conditionalStyle.hAlign(), conditionalStyle.vAlign(),
									conditionalStyle.border(), conditionalStyle.borderColor(), conditionalStyle.padding(), 
									conditionalStyle.topBorder(), conditionalStyle.topBorderColor(), conditionalStyle.topPadding(), 
									conditionalStyle.leftBorder(), conditionalStyle.leftBorderColor(), 
									conditionalStyle.leftPadding(), conditionalStyle.bottomBorder(), conditionalStyle.bottomBorderColor(), 
									conditionalStyle.bottomPadding(), conditionalStyle.rightBorder(),
									conditionalStyle.rightBorderColor(), conditionalStyle.rightPadding(), conditionalStyle.rotation(), 
									conditionalStyle.lineSpace(), conditionalStyle.isStyledText(), 
									conditionalStyle.fontName(), conditionalStyle.fontSize(), conditionalStyle.isBold(), conditionalStyle.isUnderline(), 
									conditionalStyle.isItalic(), conditionalStyle.isStrikeThrough(),
									conditionalStyle.pdfFontName(), conditionalStyle.pdfEncoding(), conditionalStyle.isPdfEmbedded(), 
									conditionalStyle.pattern(), conditionalStyle.isBlankWhenNull(), conditionalStyle.conditionalExpression(),
									conditionalStyle.lineWidth() > -1?conditionalStyle.lineWidth():conditionalStyle.topLineWidth(),
									conditionalStyle.lineWidth() > -1?conditionalStyle.lineWidth():conditionalStyle.leftLineWidth(),
									conditionalStyle.lineWidth() > -1?conditionalStyle.lineWidth():conditionalStyle.bottomLineWidth(),
									conditionalStyle.lineWidth() > -1?conditionalStyle.lineWidth():conditionalStyle.rightLineWidth(),
									StringUtils.isNotBlank(conditionalStyle.lineColor())?conditionalStyle.lineColor():conditionalStyle.topLineColor(),
									StringUtils.isNotBlank(conditionalStyle.lineColor())?conditionalStyle.lineColor():conditionalStyle.leftLineColor(),
									StringUtils.isNotBlank(conditionalStyle.lineColor())?conditionalStyle.lineColor():conditionalStyle.bottomLineColor(),
									StringUtils.isNotBlank(conditionalStyle.lineColor())?conditionalStyle.lineColor():conditionalStyle.rightLineColor(),
									conditionalStyle.isDefault()
							);
							conditionalStyleInfos.add(conditionalStyleInfo);
						}
						styleInfo.setConditionalStyleInfos(conditionalStyleInfos);
						styleInfos.add(styleInfo);
					}
				}
			}
		} catch (Exception e) {
			logger.error("[Kismiss:setStyles] Error happended => {}", e.getMessage());
			throw new KismissException(e);
		}
	}
	
	/**
	 * @param property
	 * @param method
	 * @param positions
	 * @throws Exception
	 * used for dynamic grouping, we collect the field/s
	 */
	protected void potensialFieldProcessing(Property property, Method method, List<Integer> positions, List<String> potentialFields) 
		throws KismissException{
		try {
			if(property.innerProperty().length > 0){
				String[] innerProperties = property.innerProperty();
				String[] divideProperty;
				if(property.position().length != innerProperties.length){
					String[] errors = new String[2];
					errors[0] = method.getName() +"' '"+ String.valueOf(property.position().length);
					errors[1] = String.valueOf(innerProperties.length);
					throw new KismissException(ErrorHandler.getInstance().errorNotValid(errors, ErrorCode.INVALID_NUMBER_OF_POSITION));
				}
				for (int i = 0; i < innerProperties.length; i++) {
					boolean include; //, showDetail;
					try {
						include = property.isInclude()[i];
					} catch (Exception e1) {
						include = property.isInclude()[0];
					}
//					try {
//						showDetail = property.isShowInDetail()[i];
//					} catch (Exception e1) {
//						showDetail = property.isShowInDetail()[0];
//					}
					if(include){ // && showDetail){
						String innerProperty = innerProperties[i];
						divideProperty = StringUtils.split(innerProperty, ":");
						if(divideProperty.length != 2){
							throw new KismissException(ErrorHandler.getInstance().
									errorNotValid(innerProperty, ErrorCode.INVALID_INNER_PROPERTY));
						}			
	//					try {
	//						Integer.parseInt(divideProperty[2].trim());
	//					} catch (Exception e) {
	//						throw new KismissException(ErrorHandler.getInstance()
	//								.errorNotValid(innerProperty, ErrorCode.INVALID_INNER_PROPERTY_WIDTH));
	//					}
						potentialFields.add(MethodUtils.getFieldName(method.getName())+"."+divideProperty[1]);
						positions.add(property.position()[i]);
					}
				}
			}else{
				if(property.isInclude()[0]) { // && property.isShowInDetail()[0]){
					potentialFields.add(MethodUtils.getFieldName(method.getName()));
					positions.add(property.position()[0]);
				}
			}
		} catch (Exception e) {
			logger.error("[Kismiss:potensialFieldProcessing] Error happended => {}", e.getMessage());
			throw new KismissException(e);
		}
	}
	
	/**
	 * @param groups
	 * find the leftest position for x, this is for calculation positioning
	 * @throws Exception 
	 */
	protected void leftestCalculationX(List<GroupInfo> groups) throws Exception {
		try {
			for (GroupInfo group : groups) {
				if(group.getGroupCals() != null && group.getGroupCals().size() > 0){
					int size = 0;
					for (CalculationInfo calc : group.getGroupCals()) {
						if(StringUtils.isNotEmpty(calc.getAttribute()))
							size++;
					}
					int[] xValues = new int[size];
					int index =0;
					for (CalculationInfo calc : group.getGroupCals()) {
						if(StringUtils.isNotEmpty(calc.getAttribute()))
						{
							xValues[index] = calc.getXValue();
							index++;
						}
					}
					Arrays.sort(xValues);
					try {
						group.setxLeftest(xValues[0]);
					} catch (Exception e) {
						group.setxLeftest(0); // no calculation for this group
						group.setGroupCals(null);
					}
				}
			}
		} catch (Exception e) {
			logger.error("[Kismiss:leftestCalculationX] Error happended => {}", e.getMessage());
			throw new Exception(e);
		}
	}
	
	/**
	 * @param infos
	 * @param columnHeaders
	 * this method used for handling column header with multiple rows
	 * @throws ReportDataAccessException 
	 */
	protected void multiRowHeaderHandler(List<PropertyInfo> infos, List<PropertyInfo> columnHeaders, Header header) 
		throws ReportDataAccessException {
		try {
			//mapParty used for collect the same dependy row
			Map<String, PropertyInfo> mapParty = new HashMap<String, PropertyInfo>();
			//collect the all x position then use the smallest
			Map<String, List<Integer>> xCollection = new HashMap<String, List<Integer>>();
			
			for (PropertyInfo attributeInfo : infos) {
				if(attributeInfo.isShowInDetail()){
					PropertyInfo att = (PropertyInfo) attributeInfo.clone();
					att.setFontSize(header.columnHeaderFontSize());
					att.setHeight(header.columnHeaderHeight());
					att.setVerticalAlignment(VerticalAlignment.Middle);
					att.setBgColor(header.columnHeaderColor());
					att.setColorMode(StringUtils.isEmpty(header.columnHeaderColor())?ColorMode.Transparent:ColorMode.Opaque);
					att.setLineWidth(header.lineWidth() > 0 ? header.lineWidth() : att.getLineWidth());
					att.setWhenHeaderHAlignment(attributeInfo.equals(HorizontalAlignment.None)?
							header.textAlignment():attributeInfo.getWhenHeaderHAlignment());
					
					if(!ArrayUtils.isEmpty(attributeInfo.getColumnHierarchy()))
					{	
						String[] multiRow = attributeInfo.getColumnHierarchy();								
						//the real field(attribute) position Y changed
						int[] heights = attributeInfo.getHeightPortion();
						
						if(multiRow.length != heights.length || heights[0] == 0){
							throw new ReportDataAccessException(ErrorHandler.getInstance().inconsistenceHierarchy
									(attributeInfo.getAttributeName(), multiRow.length, heights[0] == 0?0 : heights.length));
						}
						
						att.setY(ArrayUtilz.sumArray(heights));
						att.setHeight(att.getHeight() - ArrayUtilz.sumArray(attributeInfo.getHeightPortion()));
	
						int index = 0;
						for (String headerName : multiRow) {					
							String bareName = headerName.trim();	
							PropertyInfo info = new PropertyInfo(
									bareName, attributeInfo.getX(), index==0?0:ArrayUtilz.sumArray(heights,index-1), 
									attributeInfo.getWidth(), attributeInfo.getHeightPortion()[index], "key-"+index,
									bareName, attributeInfo.getColumn(), attributeInfo.isOverFlow(), 
									attributeInfo.isBlankWhenNull(), attributeInfo.getEvaluationTime(), attributeInfo.getHyperlinkType(),
									attributeInfo.getHyperlinkTarget(), header.columnHeaderFontSize(), attributeInfo.getFont(), 
									header.columnHeaderColor(), StringUtils.isEmpty(header.columnHeaderColor())?ColorMode.Transparent:ColorMode.Opaque, 
									attributeInfo.getLeftPadding(), attributeInfo.getRightPadding(), attributeInfo.getTopPadding(), 
									attributeInfo.getBottomPadding(), attributeInfo.getLineWidth() > 0?attributeInfo.getLineWidth():header.lineWidth(),
									attributeInfo.getLineStyle(), HorizontalAlignment.Center, VerticalAlignment.Middle, multiRow, 
									attributeInfo.getWidthPortion(), attributeInfo.getHeightPortion(), attributeInfo.getPattern(), 
									attributeInfo.getType(), attributeInfo.isRecordNumber(), attributeInfo.getBorder(), attributeInfo.getBorderColor(), 
									attributeInfo.getPadding(), attributeInfo.getTopBorder(), attributeInfo.getTopBorderColor(), 
									attributeInfo.getLeftBorder(), attributeInfo.getLeftBorderColor(), attributeInfo.getRightBorder(),
									attributeInfo.getRightBorderColor(), attributeInfo.getBottomBorder(), attributeInfo.getBottomBorderColor(),
									attributeInfo.getWhenHeaderHAlignment()
									);
							
							List<Integer> listOfX =  xCollection.get(bareName);
							if(listOfX == null){
								listOfX = new ArrayList<Integer>(); //initial first x value
							}
							
							listOfX.add(info.getX());
							
							xCollection.put(bareName, listOfX);
							
							PropertyInfo compare = mapParty.get(bareName);
							
							if (compare != null) {
								compare.setWidth(compare.getWidth() + info.getWidth());
								compare.setX(Collections.min(listOfX));
							} else {
								mapParty.put(bareName, info);
							}
							index++;
						}				
					}
					columnHeaders.add(att);	
				}
			} // mapParty is additional columns hierarchy when multi row/s are defined
			
			for (Map.Entry<String, PropertyInfo> entry : mapParty.entrySet()) {
				columnHeaders.add(entry.getValue());
			}
		} catch (ReportDataAccessException e) {
			logger.error("[Kismiss:multiRowHeaderHandler] Error happended => {}", e.getMessage());
			throw new ReportDataAccessException(e);
		}
	}
	
	/**
	 * @param calculations
	 * @param infos
	 * setting calculation position, based on the column that declared to be calculated
	 * @throws KismissException 
	 */
	protected void calculationProcessing(List<CalculationInfo> calculations,
			List<PropertyInfo> infos) throws KismissException {
		try {
			for (CalculationInfo calculation : calculations) {
				boolean notExist = true;
				for (PropertyInfo info : infos) {
					if (calculation.getAttribute().equals(info.getAttributeName())) {
						calculation.setXValue(info.getX());
						calculation.setYValue(info.getY());
						calculation.setWidth(info.getWidth());
						calculation.setHeight(info.getHeight());
						notExist = false;
						break;				
					}
				}
				if(notExist){
					throw new KismissException(ErrorHandler.getInstance().invalid(calculation.getAttribute(), ErrorCode.INVALID_CALCULATION_PROPERTY));
				}
			}
			Collections.sort(calculations, new XComparator());
		} catch (Exception e) {
			logger.error("[Kismiss:calculationProcessing] Error happended => {}", e.getMessage());
			throw new KismissException(e);
		}
	}
	
}
