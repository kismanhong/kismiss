package com.softtech.kismiss.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softtech.kismiss.constant.CommonConstant;
import com.softtech.kismiss.constant.ReportFactory;
import com.softtech.kismiss.enumer.CalculationType;
import com.softtech.kismiss.enumer.ColorMode;
import com.softtech.kismiss.enumer.FontType;
import com.softtech.kismiss.enumer.HorizontalAlignment;
import com.softtech.kismiss.enumer.VerticalAlignment;
import com.softtech.kismiss.exception.KismissException;
import com.softtech.kismiss.exception.ReportDataAccessException;
import com.softtech.kismiss.handler.ErrorCode;
import com.softtech.kismiss.handler.ErrorHandler;
import com.softtech.kismiss.model.CalculationInfo;
import com.softtech.kismiss.model.GroupInfo;
import com.softtech.kismiss.model.PropertyInfo;
import com.softtech.kismiss.model.StyleInfo;
import com.softtech.kismiss.property.Detail;
import com.softtech.kismiss.property.Group;
import com.softtech.kismiss.property.Groups;
import com.softtech.kismiss.property.Header;
import com.softtech.kismiss.property.Kismiss;
import com.softtech.kismiss.property.Property;
import com.softtech.kismiss.property.RecordNumber;
import com.softtech.kismiss.utils.ArrayUtilz;
import com.softtech.kismiss.utils.MethodUtils;
import com.softtech.kismiss.utils.ReportUtil;


/**
 * @author Kisman Hong 
 * @email kismanhong@gmail.com
 * Softtech Inc. Dream Company
 * form the report with the information given
 */

//(java.lang.Boolean | java.lang.Byte | java.util.Date | java.sql.Timestamp
//		| java.sql.Time | java.lang.Double | java.lang.Float | java.lang.Integer
//		| java.lang.Long | java.lang.Short | java.math.BigDecimal | java.lang.Number
//		| java.lang.String) "java.lang.String")

public abstract class KismissFrontCore extends KismissMiddleCore {
	
	private final static Logger logger = LoggerFactory.getLogger(KismissFrontCore.class);

	/**
	 * variable x is for declaring the x position or horizontal position of the
	 * report
	 */
	private int x = 0;

	/**
	 * variable y is for declaring the y position or vertical position of the
	 * report
	 */
	private int y = 0;
	
	/**
	 * total width all column
	 */
	private int total = 0;

	/**
	 * variable for calculate autowidth
	 */
	private int limited = 0;

	/**
	 * number of columns
	 */
	private int numberOfColums = 0;

	/**
	 * auto width processing, for indicate where the index is
	 */
	private int indexIndicator = 0;
	
	
	/**
	 * used for dynamic fields show
	 */
	private String[] fieldNames;
	
	/**
	 * last sum width
	 */
	private int finalWidth = 0;
	
	/**
	 * used for put fields temporary
	 */
	private List<String> potentialFields = new ArrayList<String>();
	
	/**
	 * @param fieldNames
	 * define the field to be show, null mean every field will be shown
	 */
	protected void setFieldNames(String[] fieldNames) {
		this.fieldNames = fieldNames;
	}

	//initialization
	private void reset(){
		x				=	0;
		y				=	0;
		total			=	0;
		limited			=	0;
		numberOfColums	=	0;
		indexIndicator	=	0;
		finalWidth		= 	0;
	}
	
	/**
	 * @param clazz
	 * @param infos
	 * @param reportParams
	 * @param groups
	 * @param cals
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ReportDataAccessException
	 *             getting all information from POJO
	 * @throws ClassNotFoundException 
	 */
	protected void putAnnotatedData(Class<?> clazz, List<PropertyInfo> infos, List<PropertyInfo> columnHeaders,
			Map<String, Object> reportParams, Map<String, Object> headerParams,  List<GroupInfo> groups,
			List<CalculationInfo> cals, List<StyleInfo> styles,
			Map<Object, Object> helperMap, com.softtech.kismiss.access.Group...groupObjects) throws Exception {
		
		/**
		 * reset all the variable
		 */
		reset();

		/**
		 * setting the header param
		 */
		Kismiss kismiss = setReportParams(clazz, reportParams);
		
		/**
		 * kismiss is mandatory
		 */
		if(kismiss == null){
			throw new ReportDataAccessException(ErrorHandler.getInstance()
					.mustBeDeclared(clazz, ErrorCode.KISMISS_MUST_BE_DECLARED));
		}
		
		/**
		 * fetch all the field
		 */
		setPotentialFieldsAndValidatePosition(clazz);
		
		/**
		 * validate the defined fields (dynamic field)
		 */
		validateSpecifiedField(clazz);
		
		/**
		 * total width calculation
		 */
		totalWidth(clazz);
		
		/**
		 * setting header param
		 */
		Header header = setHeaderParams(clazz, headerParams);
		
		/**
		 * header is mandatory
		 */
		if(header == null){
			throw new ReportDataAccessException(ErrorHandler.getInstance()
					.mustBeDeclared(clazz, ErrorCode.HEADER_MUST_BE_DECLARED));
		}
		
		/**
		 * setting detail param
		 */
		Detail detail = setDetailParams(clazz, headerParams);
		
		/**
		 * detail is mandatory
		 */
		if(detail == null){
			throw new ReportDataAccessException(ErrorHandler.getInstance()
					.mustBeDeclared(clazz, ErrorCode.DETAIL_MUST_BE_DECLARED));
		}
			
		setStyles(clazz, styles);	
		
		/**
		 * if the all column width more than declared
		 */
		if (!kismiss.columnAutoSize()) {
			if ((Integer) reportParams.get(ReportFactory.COLUMN_WIDTH) < total)
				throw new ReportDataAccessException(ErrorHandler.getInstance()
						.maxColumnWidthReached((Integer) reportParams.get(ReportFactory.COLUMN_WIDTH), total));
		}
		/**
		 * vm template file to be used
		 */
		helperMap.put("ReportType","simple");
		/**
		 * validate and setting the right value in it
		 */
		setAttributes(clazz, infos, columnHeaders, groups, cals, kismiss, header, detail, reportParams.get(ReportFactory.COLUMN_WIDTH),
				(Boolean) helperMap.get(CommonConstant.GROUP_VISIBLE), groupObjects);
	}
	
	/**
	 * @param clazz
	 * @param infos
	 * @param groups
	 * @param cals
	 * @param kismiss
	 * @throws Exception 
	 */
	private void setAttributes(Class<?> clazz, List<PropertyInfo> infos, List<PropertyInfo> columnHeaders,
			List<GroupInfo> groups, List<CalculationInfo> cals, Kismiss kismiss, Header header, Detail detail,
			Object columnWidth, boolean groupVisible, com.softtech.kismiss.access.Group...groupObjects)
			throws Exception {

		/* setting attribute and all info given */
		attributeProcess(clazz, infos, header, detail, cals);
		/* end setting attribute and all info given */

		/* Rearrange field based on position */
		positionAndWidthArrangement(infos, (Integer)columnWidth, kismiss.columnAutoSize());
		/* end rearrange position */

		/* Calculation processing */
		calculationProcessing(cals, infos);
		/* end calculations process */

		if(groupVisible){
			/* group processing */
			groupProcessing(clazz, groups, infos, kismiss.columnAutoSize(), detail, groupObjects);
			/* group information end here */
		}
		
		/* validate record number from property (used for record number in report) */
		validateRecordNumber(infos, groups);
		/* validate record number from property (used for record number in report) end here */
		
		/* find the leftest calculation to be print */
		leftestCalculationX(groups);
		/* find the leftest calculation to be print end here */
		
		/* multi row column header */
		multiRowHeaderHandler(infos, columnHeaders, header);
		/* multi row column header end here */
	}
	
	
	/**
	 * @param clazz
	 * @param infos
	 * @param header
	 * @param cals
	 * @throws Exception 
	 * getting attribute/field from getter method/s
	 */
	private void attributeProcess(Class<?> clazz,List<PropertyInfo> infos, Header header, Detail detail,
			List<CalculationInfo> cals) throws ReportDataAccessException
	{
		try {
			Method[] methods = clazz.getMethods();
			// getting info from POJO, information through annotation in method get
			for (Method method : methods) {
				if (MethodUtils.isGetter(method)) {
					Annotation[] annotations = method.getAnnotations();
					//getting the annotations declared at POJO class and filter them as the rule
					for (Annotation annotation : annotations) {
						if(fieldNames == null){ // if fields are chosen by user(dynamic field to show)
							infoProcessing(annotation, infos, method, header, detail, cals);
						}else {
							if( ArrayUtilz.indexOfString(fieldNames, MethodUtils.getFieldName(method.getName()))) {
								 infoProcessing(annotation, infos, method, header, detail, cals);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("[Kismiss:attributeProcess] Error happended => {}", e.getMessage());
			throw new ReportDataAccessException(e);
		}
	}
	
	/**
	 * @param annotation
	 * @param infos
	 * @param method
	 * @param header
	 * @param cals
	 * @throws Exception 
	 * Process the getter method that contain property and calculation tag of Kismiss
	 */
	private void infoProcessing(Annotation annotation, List<PropertyInfo> infos, Method method, Header header, Detail detail,
			List<CalculationInfo> cals) 
		throws ReportDataAccessException {
		try {
			if (annotation instanceof com.softtech.kismiss.property.Property) {
				Property property = (Property) annotation;
				addPropertyInfo(property, method, infos, header, detail);
			}
			else if (annotation instanceof com.softtech.kismiss.property.Calculation ) {
				com.softtech.kismiss.property.Calculation cal = (com.softtech.kismiss.property.Calculation) annotation;
				String[] attributes = StringUtils.split(cal.attribute().equals("kismiss")?
						MethodUtils.getFieldName(method.getName()):cal.attribute(), ",");
				
				CalculationInfo calculation;
				for (int i=0; i < attributes.length; i++) {
					String calculationType = null;
				
					try {
						calculationType = cal.calculationType()[i].toString();
					} catch (Exception e) {
						calculationType =  cal.calculationType()[0].toString();
					}
					
					calculation = new CalculationInfo(
							attributes[i], MethodUtils.getReturnType( attributes[i], method.getReturnType()),
							calculationType, 0, 0, 0, 0 , cal.fontType(), cal.fontSize(), cal.verticalAlignment(),
							cal.horizontalAlignment(), cal.bold(), cal.lineWidth(), cal.lineStyle(), cal.pattern(), cal.label(),
							cal.backColor(), StringUtils.isEmpty(cal.backColor())?ColorMode.Transparent:ColorMode.Opaque,
									cal.labelHorizontalAlignment()
							);
					cals.add(calculation);
				}			
			}else if (annotation instanceof com.softtech.kismiss.property.RecordNumber){
				RecordNumber recordNumber = (RecordNumber) annotation;
				for (PropertyInfo propertyInfo : infos) {
					if(MethodUtils.getFieldName(method.getName()).equals(propertyInfo.getAttributeName())){
						propertyInfo.setRecordNumber(true);
						propertyInfo.setResetWhenGrouped(recordNumber.isResetWhenGrouped());
						propertyInfo.setResetGroupName(recordNumber.resetGroupName());
					}
				}
			}
		} catch (Exception e) {
			logger.error("[Kismiss:infoProcessing] Error happended => {}", e.getMessage());
			throw new ReportDataAccessException(e);
		}
	}
	
	
	/**
	 * @param property
	 * @param method
	 * @param infos
	 * @param header
	 * @param detail
	 * @throws ReportDataAccessException
	 * When valid, add that property to List of property
	 */
	private void addPropertyInfo(Property property, Method method, List<PropertyInfo> infos, Header header, Detail detail) 
		throws ReportDataAccessException {
		try {
			String propertyName;
//			if (property.isInclude()) {
				if(property.innerProperty().length > 0 ){
					String[] innerProperties = property.innerProperty();
					String[] divideProperty;			
					int len = innerProperties.length;
					HorizontalAlignment[] horizontalAlignments = new HorizontalAlignment[len];
					VerticalAlignment[] verticalAlignments = new VerticalAlignment[len];
					String[] patterns = new String[len];
					String[] styles = new String[len];
					int[] width = new int[len];
					int[] fontSize = new int[len];
					FontType[] fontTypes = new FontType[len];
					String[] foreColors = new String[len];
					String[] textFieldExpression = new String[len];
					boolean[] isPrintWhenDetailOverFlow = new boolean[len];
					String[] printWhenExpression = new String[len];
					boolean[] isPrintRepeatedValues = new boolean[len];
					boolean[] isRemoveLineWhenBlank = new boolean[len];
					boolean[] isBold = new boolean[len];
					boolean[] isUnderline = new boolean[len];
					boolean[] isItalic= new boolean[len];
					boolean[] isStrikeThrough= new boolean[len];
					String[] bgColor = new String[len];
					ColorMode[] colorMode = new ColorMode[len];
					boolean[] isStretchWithOverflow= new boolean[len];
					boolean[] isBlankWhenNull= new boolean[len];
					int[] padding = new int[len];
					int[] leftPadding = new int[len];
					int[] rightPadding = new int[len];
					int[] topPadding = new int[len];
					int[] bottomPadding = new int[len];
//					boolean[] isShowDetail= new boolean[len];
					
					for (int i = 0; i < len; i++) {
						boolean include, showDetail;
						try {
							include = property.isInclude()[i];
						} catch (Exception e1) {
							include = property.isInclude()[0];
						}
						try {
							showDetail = property.isShowInDetail()[i];
						} catch (Exception e1) {
							showDetail = property.isShowInDetail()[0];
						}
						if(include) {  //&& showDetail){
							String innerProperty = innerProperties[i];
							divideProperty = StringUtils.split(innerProperty, ":");
							if(divideProperty.length != 2){
								throw new KismissException(ErrorHandler.getInstance().errorNotValid(innerProperty, ErrorCode.INVALID_INNER_PROPERTY));
							}
							
							try {
								horizontalAlignments[i] = property.horizontalAlignment()[i];
							} catch (Exception e2) {
								horizontalAlignments[i] = property.horizontalAlignment()[0];
							}
							
							try {
								verticalAlignments[i] = property.verticalAlignment()[i];
							} catch (Exception e2) {
								verticalAlignments[i] = property.verticalAlignment()[0];
							}
							
							try {
								patterns[i] = property.pattern()[i];
							} catch (Exception e2) {
								patterns[i] = property.pattern()[0];
							}
							
							try {
								styles[i] = property.style()[i];
							} catch (Exception e2) {
								styles[i] = "";
							}
							
							try {
								width[i] = property.width()[i];
							} catch (Exception e2) {
								width[i] = property.width()[0];
							}
							
							String prefix="";
							String postfix="";
							
							try {
								prefix  = property.prefix()[i];
							} catch (Exception e1) {
								prefix  = property.prefix()[0];
							}
							
							try {
								postfix =property.postfix()[i];
							} catch (Exception e1) {
								postfix =property.postfix()[0];
							}
							
							try {
								fontSize[i] = property.fontSize()[i];
							} catch (Exception e2) {
								fontSize[i] = property.fontSize()[0];
							}
							
							try {
								fontTypes[i] = property.font()[i];
							} catch (Exception e2) {
								fontTypes[i] = property.font()[0];
							}
							
							try {
								foreColors[i] = property.foreColor()[i];
							} catch (Exception e2) {
								foreColors[i] = property.foreColor()[0];
							}
							
							try {
								textFieldExpression[i] = property.textFieldExpression()[i];
							} catch (Exception e2) {
								textFieldExpression[i] = property.textFieldExpression()[0];
							}
							
							try {
								isPrintWhenDetailOverFlow[i] = property.isPrintWhenDetailOverFlow()[i];
							} catch (Exception e2) {
								isPrintWhenDetailOverFlow[i] = property.isPrintWhenDetailOverFlow()[0];
							}
							
							try {
								printWhenExpression[i] = property.printWhenExpression()[i];
							} catch (Exception e2) {
								printWhenExpression[i] = property.printWhenExpression()[0];
							}
							
							try {
								isPrintRepeatedValues[i] = property.isPrintRepeatedValues()[i];
							} catch (Exception e2) {
								isPrintRepeatedValues[i] = property.isPrintRepeatedValues()[0];
							}
							
							try {
								isRemoveLineWhenBlank[i] = property.isRemoveLineWhenBlank()[i];
							} catch (Exception e2) {
								isRemoveLineWhenBlank[i] = property.isRemoveLineWhenBlank()[0];
							}
							
							try {
								isBold[i] = property.isBold()[i];
							} catch (Exception e2) {
								isBold[i] = property.isBold()[0];
							}
							
							try {
								isUnderline[i] = property.isUnderline()[i];
							} catch (Exception e2) {
								isUnderline[i] = property.isUnderline()[0];
							}
							
							try {
								isItalic[i] = property.isItalic()[i];
							} catch (Exception e2) {
								isItalic[i] = property.isItalic()[0];
							}
							
							try {
								isStrikeThrough[i] = property.isStrikeThrough()[i];
							} catch (Exception e2) {
								isStrikeThrough[i] = property.isStrikeThrough()[0];
							}
							
							try {
								bgColor[i] = property.bgColor()[i];
							} catch (Exception e2) {
								bgColor[i] = property.bgColor()[0];
							}
							
							try {
								colorMode[i] = property.colorMode()[i];
							} catch (Exception e2) {
								colorMode[i] = property.colorMode()[0];
							}
						
							try {
								isStretchWithOverflow[i] = property.isStretchWithOverflow()[i];
							} catch (Exception e2) {
								isStretchWithOverflow[i] = property.isStretchWithOverflow()[0];
							}
							
							try {
								isBlankWhenNull[i] = property.isBlankWhenNull()[i];
							} catch (Exception e2) {
								isBlankWhenNull[i] = property.isBlankWhenNull()[0];
							}
							
							try {
								padding[i] = property.padding()[i];
							} catch (Exception e2) {
								padding[i] = property.padding()[0];
							}
							
							try {
								leftPadding[i] = property.leftPadding()[i];
							} catch (Exception e2) {
								leftPadding[i] = property.leftPadding()[0];
							}
							
							try {
								rightPadding[i] = property.rightPadding()[i];
							} catch (Exception e2) {
								rightPadding[i] = property.rightPadding()[0];
							}
						
							try {
								topPadding[i] = property.topPadding()[i];
							} catch (Exception e2) {
								topPadding[i] = property.topPadding()[0];
							}
							
							try {
								bottomPadding[i] = property.bottomPadding()[i];
							} catch (Exception e2) {
								bottomPadding[i] = property.bottomPadding()[0];
							}
							
							propertyName = MethodUtils.getFieldName(method.getName()) + ("".equals(property.innerProperty())==true?"":"."+ divideProperty[1]);
							infos.add(new PropertyInfo(
									propertyName , x, y, width[i], 
									property.height()==0?detail.height():property.height(), property.key(),  divideProperty[0], property.position()[i], 
									isStretchWithOverflow[i], isBlankWhenNull[i],property.evaluationTime().toString(), 
									property.hyperlinkType(), property.hyperlinkTarget().toString(), fontSize[i], 
									fontTypes[i].toString().replaceAll("_", "-"), bgColor[i], colorMode[i], leftPadding[i], 
									rightPadding[i], topPadding[i], bottomPadding[i], property.lineWidth() > 0?
									property.lineWidth():detail.lineWidth() , property.lineStyle().toString(), horizontalAlignments[i],
									verticalAlignments[i], property.columnHierarchy(), property.widthPortion(), property.heightPortion(), 
									patterns[i], MethodUtils.getReturnType(propertyName, method.getReturnType()), 
									false, false, "", property.border(), property.borderColor(), padding[i], property.topBorder(),
									property.topBoderColor(), property.leftBorder(), property.leftBorderColor(), property.rightBorder(),
									property.rightBorderColor(), property.bottomBorder(), property.bottomBorderColor(),
									prefix, postfix, showDetail, 
									isPrintWhenDetailOverFlow[i], textFieldExpression[i], printWhenExpression[i], 
									isPrintRepeatedValues[i], isRemoveLineWhenBlank[i], isBold[i], isItalic[i], 
									isUnderline[i], isStrikeThrough[i], styles[i], foreColors[i],
									property.whenHeaderHAlignment().equals(HorizontalAlignment.None)?header.textAlignment():property.whenHeaderHAlignment())
							);
						}
					}
				}else{
					if(property.isInclude()[0]) { // && property.isShowInDetail()[0]){
						propertyName = MethodUtils.getFieldName(method.getName());
						infos.add(new PropertyInfo(
								propertyName , x, y, property.width()[0], 
								property.height()==0?detail.height():property.height(), property.key(), property.name(), property.position()[0], 
								property.isStretchWithOverflow()[0], property.isBlankWhenNull()[0],property.evaluationTime().toString(), property.hyperlinkType(), 
								property.hyperlinkTarget().toString(), property.fontSize()[0], property.font()[0].toString().replaceAll("_", "-"), 
								property.bgColor()[0], property.colorMode()[0], property.leftPadding()[0], property.rightPadding()[0], property.topPadding()[0],
								property.bottomPadding()[0], property.lineWidth() > 0?property.lineWidth():detail.lineWidth(), property.lineStyle().toString(), 
								property.horizontalAlignment()[0], property.verticalAlignment()[0], property.columnHierarchy(), property.widthPortion(), 
								property.heightPortion(), property.pattern()[0], MethodUtils.getReturnType(propertyName, method.getReturnType()),
								false, false, "", property.border(), property.borderColor(), property.padding()[0], property.topBorder(),
								property.topBoderColor(), property.leftBorder(), property.leftBorderColor(), property.rightBorder(),
								property.rightBorderColor(), property.bottomBorder(), property.bottomBorderColor(),
								property.prefix()[0], property.postfix()[0], 
								property.isShowInDetail()[0], property.isPrintWhenDetailOverFlow()[0], property.textFieldExpression()[0], 
								property.printWhenExpression()[0], property.isPrintRepeatedValues()[0], property.isRemoveLineWhenBlank()[0],property.isBold()[0], 
								property.isItalic()[0], property.isUnderline()[0], property.isStrikeThrough()[0], property.style()[0], property.foreColor()[0], 
								property.whenHeaderHAlignment().equals(HorizontalAlignment.None)?header.textAlignment():property.whenHeaderHAlignment())
						);
					}
				}			
//			}
		} catch (Exception e) {
			logger.error("[Kismiss:addProperty] Error happended => {}, property : {}", e.getMessage(), property.name());
			throw new ReportDataAccessException(e);
		}
	}
	
	/**
	 * @param infos
	 * @param groups
	 * @param clazz
	 * @param columnAutoSize
	 * @param group
	 * @param detail
	 * @param calculations
	 * @throws ReportDataAccessException
	 * add group to list
	 */
	@SuppressWarnings("rawtypes")
	protected void addGroup(
			List<PropertyInfo> infos, List<GroupInfo> groups, Class clazz,
			boolean columnAutoSize, com.softtech.kismiss.property.Group group, 
			Detail detail, String[] calculations) throws ReportDataAccessException {
		try {
			List<CalculationInfo> cals = new ArrayList<CalculationInfo>();
			for (String string : calculations) {
				String[] grpCal = string.split(":");
				if (grpCal.length < 2 || !CalculationType.contains(grpCal[1]))
					throw new ReportDataAccessException(ErrorHandler.getInstance()
							.invalid(clazz.getCanonicalName(),ErrorCode.INVALID_DEFINED_CALCUTION));
				
				String pattern = "";
				try {
					pattern = grpCal[2];
				} catch (Exception e) {} // ignore
				
				Object[] dimention = ReportUtil.getInstance().getValues(infos, grpCal[0].trim(), group.groupBy(), clazz);
				CalculationInfo cal = new CalculationInfo
									( grpCal[0].trim(), dimention[4].toString(),  grpCal[1].trim(), 
									(Integer) dimention[0], (Integer) dimention[1],(Integer) dimention[2],
									(Integer) dimention[3], detail.lineWidth(), group.horizontalAlignment(), group.labelHorizontalAlignment(), 
									StringUtils.isEmpty(pattern)?dimention[5].toString():pattern);
				cals.add(cal);
			}
			groups.add(new GroupInfo(group.groupBy(), group.bandHeight(), group.x(), group.y(), group.height(),
					0, cals, group.calculationPrintType(), group.isBold(), group.font(), group.fontSize(), 
					group.leftPadding(), group.lineWidth() > 0? group.lineWidth() : detail.lineWidth(), group.backColor(),
					StringUtils.isEmpty(group.backColor())?ColorMode.Transparent:ColorMode.Opaque,
					group.horizontalAlignment(),group.labelHorizontalAlignment(), group.lineStyle(), group.verticalAlignment(), 
					group.groupLabel(), group.labelPrefix(), group.labelSuffix()));
		} catch (ReportDataAccessException e) {
			logger.error("[Kismiss:addGroup] Error happended => {}", e.getMessage());
			throw new ReportDataAccessException(e);
		}
	}
	
	/**
	 * @param groupBy
	 * @param headerHeight
	 * @param xValue
	 * @param yValue
	 * @param height
	 * @param groupCals
	 * @param infos
	 * @param groups
	 * @throws ReportDataAccessException
	 */
	protected void addGroup(
			List<PropertyInfo> infos, List<GroupInfo> groups, Class<?> clazz,
			boolean columnAutoSize, com.softtech.kismiss.access.Group group, double lineWidth) throws ReportDataAccessException {
		try {
			List<CalculationInfo> cals = new ArrayList<CalculationInfo>();
			if(group.getCalculations() != null && group.getCalculations().size() > 0){
				for (com.softtech.kismiss.access.Calculation calculation : group.getCalculations()) {
					Object[] dimention = ReportUtil.getInstance().findMatchAttribute(infos , calculation.getAttribute(), clazz);
					CalculationInfo cal = new CalculationInfo(
										calculation.getAttribute(), dimention[4].toString(), calculation.getCalculationType().toString(), 
										(Integer)dimention[0], 
										(Integer)dimention[1], (Integer)dimention[2], (Integer) dimention[3], calculation.getFontType(), 
										calculation.getFontSize(), calculation.getVerticalAlignment(), calculation.getHorizontalAlignment(),
										calculation.isBold(), lineWidth, calculation.getLineStyle(), calculation.getPattern(),
										calculation.getLabel(), calculation.getBackColor(), StringUtils.isEmpty(calculation.getBackColor())?
										ColorMode.Transparent:ColorMode.Opaque, calculation.getLabelHorizontalAlignment()
										);
					cals.add(cal);
				}
			}

			groups.add(new GroupInfo(group.getGroupBy(), group.getBandHeight(), 0, 0, group.getHeight(), 0, cals, group.getCalculationPrintType(),
					group.isBold(), group.getFont(), group.getFontSize(), group.getLeftPadding(), group.getLineWidth() > 0?group.getLineWidth(): 
						lineWidth, group.getBackColor(), 
					StringUtils.isEmpty(group.getBackColor())?ColorMode.Transparent:ColorMode.Opaque, group.getHorizontalAlignment(),
							group.getLabelHorizontalAlignment(), group.getLineStyle(), group.getVerticalAlignment(), 
							group.getGroupLabel(), group.getLabelPrefix(), group.getLabelSuffix()));
		} catch (Exception e) {
			logger.error("[Kismiss:addGroup] Error happended => {}", e.getMessage());
			throw new ReportDataAccessException(e);
		}
	}

	/**
	 * @param columnWidth
	 * @param percentage
	 * @return int process the width, get the percentage size of all
	 */
	private int autoWidthProcessing(int columnWidth, double percentage) {
		indexIndicator++;
		double width = percentage / total * columnWidth;
		int process = 0;
		Double doubleValue = new Double(Math.abs(width));
		process = doubleValue.intValue();
		if (indexIndicator == numberOfColums){
			process = columnWidth - limited;
		}
		limited += process;
		return process;
	}

	/**
	 * @param clazz
	 *            total width
	 *            sum the total column size
	 */
	@SuppressWarnings("rawtypes")
	private void totalWidth(Class clazz) throws Exception {
		try {
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				if (MethodUtils.isGetter(method)) {
					Annotation[] annotations = method.getAnnotations();
					for (Annotation annotation : annotations) {
						if (annotation instanceof com.softtech.kismiss.property.Property) {
							Property property = (Property) annotation;
//						if (property.isInclude()  && property.isShowInDetail()) {
								totalWidthProcess(property, method);
//						}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("[Kismiss:totalWidth] Error happended => {}", e.getMessage());
			throw new Exception(e);
		}
	}
	
	/**
	 * @param property
	 * @param method
	 * @throws Exception
	 */
	private void totalWidthProcess(Property property, Method method) throws Exception{
		try {
			if (fieldNames == null) {
				if(property.innerProperty().length > 1){
					String[] innerProperties = property.innerProperty();
					for (int i = 0; i < innerProperties.length; i++) {	
						boolean include, showDetail;
						try {
							include = property.isInclude()[i];
						} catch (Exception e1) {
							include = property.isInclude()[0];
						}
						try {
							showDetail = property.isShowInDetail()[i];
						} catch (Exception e1) {
							showDetail = property.isShowInDetail()[0];
						}
						if(include && showDetail){
							try {
								total += property.width()[i];
							} catch (Exception e) {
								total += property.width()[0];
//						throw new KismissException(ErrorHandler.getInstance().errorNotValid(innerProperty, ErrorCode.INVALID_INNER_PROPERTY_WIDTH));
							}
							numberOfColums++;
						}
					}
				}else{
					if(property.isInclude()[0] && property.isShowInDetail()[0]){
						total += property.width()[0];
						numberOfColums++;
					}
				}									
			} else {
				if (ArrayUtilz.indexOfString(fieldNames, MethodUtils.getFieldName(method.getName()))) {
					if(property.innerProperty().length > 1){
						String[] innerProperties = property.innerProperty();
						for (int i = 0; i < innerProperties.length; i++) {
							boolean include, showDetail;
							try {
								include = property.isInclude()[i];
							} catch (Exception e1) {
								include = property.isInclude()[0];
							}
							try {
								showDetail = property.isShowInDetail()[i];
							} catch (Exception e1) {
								showDetail = property.isShowInDetail()[0];
							}
							if(include && showDetail){
								try {
									total += property.width()[i];
								} catch (Exception e) {
									total += property.width()[0];
//							throw new KismissException(ErrorHandler.getInstance().errorNotValid(innerProperty, ErrorCode.INVALID_INNER_PROPERTY_WIDTH));
								}			
								numberOfColums++;
							}
						}
					}else{
						if(property.isInclude()[0] && property.isShowInDetail()[0]){
							total += property.width()[0];
							numberOfColums++;
						}
					}	
					
				}
			}
		} catch (Exception e) {
			logger.error("[Kismiss:totalWidthProcess] Error happended => {}", e.getMessage());
			throw new Exception(e);
		}
	}
	
	/**
	 * @param clazz
	 * record all fields
	 * @throws ReportDataAccessException 
	 */
	private void setPotentialFieldsAndValidatePosition(Class<?> clazz) throws ReportDataAccessException {
		try {
			Method[] methods = clazz.getMethods();
			List<Integer> positions = new ArrayList<Integer>();
			for (Method method : methods) {
				if (MethodUtils.isGetter(method)) {
					Annotation[] annotations = method.getAnnotations();
					for (Annotation annotation : annotations) {
						if (annotation instanceof com.softtech.kismiss.property.Property) {
							Property property = (Property) annotation;
//							if (property.isInclude()) { 
								if (fieldNames == null) {
									potensialFieldProcessing(property, method, positions, potentialFields);
								}else {
									if (ArrayUtilz.indexOfString(fieldNames, MethodUtils.getFieldName(method.getName()))) {
										potensialFieldProcessing(property, method, positions, potentialFields);
									}
								}
//							}
						}
					}
				}
			}
			if(fieldNames == null){ // if dynamic fields are applied, then validate position is ignored
				Integer[] position = (Integer[]) positions.toArray(new Integer[0]);
				Arrays.sort(position);
				int compare = 0;
				for (Integer integer : position) {
					if(integer != compare){
						throw new ReportDataAccessException(ErrorHandler.getInstance().invalidPosition(integer, compare));
					}
					compare++;
				}
			}
		} catch (Exception e) {
			logger.error("[Kismiss:setPotensialFieldAndValidatePosition] Error happended => {}", e.getMessage());
			throw new ReportDataAccessException(e);
		}
	}
	

	
	/**
	 * @throws ReportDataAccessException
	 * if dynamic fields, validate the specified field valid or not
	 */
	private void validateSpecifiedField(Class<?> clazz) throws ReportDataAccessException {
		try {
			if(fieldNames != null && fieldNames.length > 0) {
//			Field[] fields = clazz.getDeclaredFields();
				for (String field : fieldNames) {
//				String[] shadowFields = StringUtils.split(field, ".");
//				if(shadowFields.length == 1){
						if(!potentialFields.contains(field)) {
							throw new ReportDataAccessException(ErrorHandler.getInstance().cannotResolvedDynamicField(field, clazz));
						}
//				}else {
//					Field fld;
//					Class<?> obj = clazz;
//					for (String shd : shadowFields) {
//						try {
//							fld = obj.getDeclaredField(shd);
//							fld.setAccessible(true);							
//							obj = fld.getDeclaringClass();
////							obj = object.getClass().getClass();
//						} catch (Exception e) {
//							throw new ReportDataAccessException(ErrorHandler.getInstance().cannotResolvedDynamicField(field, clazz));
//						}
//					}
//				}
				}
			}
		} catch (Exception e) {
			logger.error("[Kismiss:validateSpecifiedField] Error happended => {}", e.getMessage());
			throw new ReportDataAccessException(e);
		}
	}

	/**
	 * @param infos
	 * @param columnWidth
	 * @param autoWidth
	 * arrange as the position given and check autoColumnSize
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void positionAndWidthArrangement(List<PropertyInfo> infos, int columnWidth, boolean autoWidth) throws Exception {
		try {
			if(fieldNames != null){
				rearrangePosition(infos);
			}
			
			List<PropertyInfo> clones = ((List) ((ArrayList<PropertyInfo>) infos).clone());
			infos.clear();
			int x = 0;
			int width = 0;
			logger.info("[Kismiss] Auto Column Width is {}", autoWidth);
			for (int i = 0; i < clones.size(); i++) {
				for (PropertyInfo clone : clones) {
					if (clone.getColumn() == i) {
						if (clone.isShowInDetail()) {
							if (autoWidth) {
								width = autoWidthProcessing(columnWidth, clone.getWidth());
							} else {
								width = clone.getWidth();
							}
							finalWidth += width;
							clone.setX(x);
							clone.setWidth(width);
							x = x + width;
						}
						infos.add(clone);
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error("[Kismiss:positionAndWidthArrangement] Error happended => {}", e.getMessage());
			throw new Exception(e);
		}
	}
	
	/**
	 * @param infos
	 * when fields are dynamicly chosen, program must rearrange attribute list
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void rearrangePosition(List<PropertyInfo> infos) throws Exception
	{
		try {
			int[] position = new int[infos.size()];
			int index = 0;
			List<PropertyInfo> clones = ((List) ((ArrayList<PropertyInfo>) infos).clone());
			for (PropertyInfo attributeInfo : clones) {
				position[index] = attributeInfo.getColumn();
				index++;
			}
			Arrays.sort(position);
			for (int i = 0; i < position.length; i++) {
				for (PropertyInfo clone : clones) {
					if (clone.getColumn() == position[i]) {
						clone.setColumn(i);
						infos.add(clone);
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error("[Kismiss:rearrangePosition] Error happended => {}", e.getMessage());
			throw new Exception(e);
		}
	}
	
	/**
	 * @param clazz
	 * @param groups
	 * @param infos
	 * @throws ReportDataAccessException
	 */
	@SuppressWarnings("rawtypes")
	private void groupProcessing(Class clazz, List<GroupInfo> groups,
			List<PropertyInfo> infos, boolean columnAutoSize, Detail detail,
			com.softtech.kismiss.access.Group...groupObjects) throws ReportDataAccessException {
		/* Getting group information */
		try {
			Annotation[] annotations = clazz.getAnnotations();

			for (Annotation annotation : annotations) {
				if (annotation instanceof com.softtech.kismiss.property.Group || 
						annotation instanceof com.softtech.kismiss.property.Groups) {
					Group[] allGroup;
					if(annotation instanceof com.softtech.kismiss.property.Group){
						allGroup = new Group[1];
						allGroup[0] = (com.softtech.kismiss.property.Group) annotation;
					} else {
						allGroup = ((Groups) annotation).groups();
					}
					
//					com.softtech.kismiss.property.Group group = (com.softtech.kismiss.property.Group) annotation;
					
					for (Group group : allGroup) {
						// when a dynamic fields chosen, if POJO specify group attribute that not specify, group will be ignore
//						if(fieldNames != null){ 
//							if(ArrayUtils.contains(fieldNames, group.groupBy()))
//							{
//								List<String> newCalc = new ArrayList<String>();
//								String[] calcs = group.calculation();
//								for (String calc : calcs) {
//									if(ArrayUtils.contains(fieldNames, calc.split(":")[0].trim())){
//										newCalc.add(calc);
//									}
//								}			    
//							    String[] calculates = (String[]) newCalc.toArray(new String[0]);
//								addGroup(infos, groups, clazz, columnAutoSize, group, detail, calculates);
//							}				
//						}
//						else {
							addGroup(infos, groups, clazz, columnAutoSize, group, detail, group.calculation());
//						}
					}					
				}
			}
			
			/* defining the Group to groups list, this is usually from code for dynamic group
			 * not from report object (POJO)
			 *  */
			for (Object object : groupObjects) {
				if(object instanceof com.softtech.kismiss.access.Group) {
					com.softtech.kismiss.access.Group group = (com.softtech.kismiss.access.Group) object;
					addGroup(infos, groups, clazz, columnAutoSize, group, detail.lineWidth());
				}else {
					//invalid property for grouping
					throw new ReportDataAccessException(ErrorHandler.getInstance().instanceGroupInvalid(object.getClass()));
				}
			}
			
			/* getting the calculation info and positioning */
			for (GroupInfo group : groups) {
				for (PropertyInfo info : infos) {
					if(info.isShowInDetail()){ // if group field is not shown, do not draw an empty of calculation/s of group
						CalculationInfo compare = new CalculationInfo();
						compare.setAttribute(info.getAttributeName());
						if (group.getGroupCals().contains(compare)) {
							for (CalculationInfo cal : group.getGroupCals()) {
								if (StringUtils.isNotEmpty(cal.getAttribute())) {
									Object[] dimention = ReportUtil.getInstance().getValues(infos, cal.getAttribute(), cal.getAttribute(), clazz);
									if (cal.getAttribute().equalsIgnoreCase(info.getAttributeName())) {
										cal.setXValue((Integer)dimention[0]);
										cal.setWidth((Integer)dimention[2]);
										break;
									}
								}
							}
						} else {
							int[] dimention = ReportUtil.getInstance().findPropertyOfMatchAttribute(infos, info.getAttributeName(), clazz);
							CalculationInfo cal = new CalculationInfo("", "", "", dimention[0],dimention[1], dimention[2], dimention[3], 
									group.getLineWidth(), group.getHorizontalAlignment(), group.getLabelHorizontalAlignment());
							group.getGroupCals().add(cal);
						}
					}
				}
				group.setWidth(columnAutoSize==true?limited:total);
			}
		} catch (Exception e) {
			logger.error("[Kismiss:groupProcessing] Error happended => {}", e.getMessage());
			throw new ReportDataAccessException(e);
		}
	}
	
	protected int getFinalWidth(){
		return finalWidth;
	}
	
	/**
	 * @param infos
	 * @param groups
	 * @throws KismissException
	 */
	private void validateRecordNumber(List<PropertyInfo> infos, List<GroupInfo> groups) throws KismissException{
		try {
			for (PropertyInfo propertyInfo : infos) {
				if(propertyInfo.isRecordNumber() && propertyInfo.isResetWhenGrouped()){
					GroupInfo groupInfo = new GroupInfo(propertyInfo.getResetGroupName());
					if(!groups.contains(groupInfo)) {
						throw new KismissException(ErrorHandler.getInstance().invalid(propertyInfo.getResetGroupName(), 
								ErrorCode.CANNOT_RESOLVED_GROUP_RECORD));
					}
				}
			}
		} catch (Exception e) {
			logger.error("[Kismiss:validateRecordNumber] Error happended => {}", e.getMessage());
			throw new KismissException(e);
		}
	}

}
