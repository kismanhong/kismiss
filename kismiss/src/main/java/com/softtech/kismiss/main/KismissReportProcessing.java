package com.softtech.kismiss.main;


public abstract class KismissReportProcessing extends KismissReportCore{
//	/**
//	 * this is for declaring name of report, if it's not concerned
//	 */
//	private static final String DEFAULT_FILE_NAME = "reports";
//
//	/**
//	 * this variable infos<AttributeInfo> is for declaring of the column name,
//	 * attribute, width/height of the column, strecthable,... use in column
//	 * header and detail most
//	 */
//	private List<AttributeInfo> infos = new ArrayList<AttributeInfo>();
//	
//	private List<AttributeInfo> columnHeaders = new ArrayList<AttributeInfo>();
//
//	/**
//	 * this variable designs<DesignInfo> is for declaring information of the
//	 * design such as : background image(size, position...)
//	 */
//	private List<DesignInfo> designs = new ArrayList<DesignInfo>();
//
//	/**
//	 * variable x is for declaring the x position or horizontal position of the
//	 * report
//	 */
//	private int x = 0;
//
//	/**
//	 * variable y is for declaring the y position or vertical position of the
//	 * report
//	 */
//	@SuppressWarnings("unused")
//	private int y = 0;
//
//	/**
//	 * this reportParams use for declaring the begin of the jasper e.g:
//	 * pageWidth, pageHeight, and the others
//	 */
//	private Map<String, Object> reportParams = new HashMap<String, Object>();
//
//	/**
//	 * Map for setting information about params
//	 */
//	private Map<String, Object> headerParams = new HashMap<String, Object>();
//
//	/**
//	 * this calculations use for declaring the agregate function for report,
//	 * e.g: sum, average, min, max
//	 */
//	private List<Calculation> calculations = new ArrayList<Calculation>();
//
//	// private List<AttributeInfo> empties = new ArrayList<AttributeInfo>();
//
//	/**
//	 * others information
//	 */
//	private Map<String, String> others = new HashMap<String, String>();
//
//	/**
//	 * this groups use for declaring the groups of the report, try to make it
//	 * multilevel
//	 */
//	private List<Group> groups = new ArrayList<Group>();
//	
//	private List<Calculation> cals = new ArrayList<Calculation>();
//
//	/**
//	 * variable for detecting position
//	 */
//	private int position = 0;
//
////	/**
////	 * this boolean call is used for preventing call twice without close()
////	 * method call, because the whole variable need to be null when the other
////	 * will be called
////	 */
////	private boolean call = false;
//	
//	/**
//	 * ResourceBundle for read kismiss properties file
//	 */
//	private ResourceBundle resourceBundle = null;
//	
//	/**
//	 * delete temp file or not
//	 */
//	private boolean deleteTempFile = true;
//	
//	/**
//	 * jrxml output path
//	 */
//	private String OUTPUT_URL_PATH = "";
//	
//	/**
//	 * constructor
//	 */
//	public KismissReportProcessing() {		
//		try {
//			resourceBundle = ResourceBundle.getBundle(CommonConstant.KISMISS_PROPERTIES);				
//			if (resourceBundle.getString(CommonConstant.OUTPUT_URL_PATH) != null) {
//				OUTPUT_URL_PATH = resourceBundle.getString(CommonConstant.OUTPUT_URL_PATH);
//				deleteTempFile = false;
//			}
//		} catch (Exception e) {
////			logger.info("kismiss.properties is not configured");
//			// no kismiss.properties configure, bypass and no jrxml file will be produced
////			e.printStackTrace();
//		}		
//	}
//	
//	/**
//	 * @param clazz
//	 * @param infos
//	 * @param groups
//	 * @param cals
//	 * @param kismiss
//	 * @throws IllegalArgumentException
//	 * @throws IllegalAccessException
//	 * @throws InvocationTargetException
//	 * @throws ReportDataAccessException
//	 *             get report attribute and hold them
//	 */
//	@SuppressWarnings("unchecked")
//	protected void initialization(Class clazz)
//			throws IllegalArgumentException, IllegalAccessException,
//			InvocationTargetException, ReportDataAccessException {
//		Kismiss kismiss = setreportParams(clazz, reportParams);
//		Band band = setHeaderParams(clazz, headerParams);
//		
//		Method[] methods = clazz.getMethods();
//		// getting info from POJO, information through annotation in method get
//		for (Method method : methods) {
//			if (MethodUtils.isGetter(method)) {
//				Annotation[] annotations = method.getAnnotations();
//				//getting the annotations declared at POJO class and filter them as the rule
//				for (Annotation annotation : annotations) {
//					if (annotation instanceof com.softtech.kismiss.property.Property) {
//						Property property = (Property) annotation;
//						if (property.isInclude()) {
//							infos.add(new AttributeInfo(
//									MethodUtils.getFieldName(method.getName()), x, y, property.width(), 
//									property.height()==0?band.detailHeight():property.height(), property.key(), property.name(), property.position(), 
//									property.isStretchWithOverflow(), property.isBlankWhenNull(),property.evaluationTime(), property.hyperlinkType(), 
//									property.hyperlinkTarget(), property.fontSize(), property.font().toString(),property.leftPadding(), 
//									property.rightPadding(), property.topPadding(), property.bottomPadding(), property.lineWidth(), 
//									property.lineStyle().toString(), property.horizontalAlignment(), property.verticalAlignment(), property.abovePosition(), 
//									property.widthPortion(), property.heightPortion(), property.pattern(), MethodUtils.getReturnType(method.getReturnType().toString()), 
//									true)
//							);
//						}
//					}
//					else if (annotation instanceof com.softtech.kismiss.property.Calculation) {
//						com.softtech.kismiss.property.Calculation cal = (com.softtech.kismiss.property.Calculation) annotation;
//						Calculation calculation = new Calculation(
//								MethodUtils.getFieldName(method.getName()),
//								cal.calculationType().toString(), 0, 0, 0, 0 
//								);
//						cals.add(calculation);
//					}
//				}
//			}
//		}
//	}
//	
//	
//	/**
//	 * @param clazz
//	 * @param infos
//	 * @param groups
//	 * @param cals
//	 * @param kismiss
//	 * @throws IllegalArgumentException
//	 * @throws IllegalAccessException
//	 * @throws InvocationTargetException
//	 * @throws ReportDataAccessException
//	 *             get report attribute and hold them
//	 */
//	@SuppressWarnings("unchecked")
//	protected void initialization(Class clazz, String[] fieldNames)
//			throws IllegalArgumentException, IllegalAccessException,
//			InvocationTargetException, ReportDataAccessException {
//		Kismiss kismiss = setreportParams(clazz, reportParams);
//		Band band = setHeaderParams(clazz, headerParams);
//		
//		Method[] methods = clazz.getMethods();
//		// getting info from POJO, information through annotation in method get
//		for (Method method : methods) {
//			if (MethodUtils.isGetter(method)) {
//				Annotation[] annotations = method.getAnnotations();
//				//getting the annotations declared at POJO class and filter them as the rule
//				for (Annotation annotation : annotations) {
//					if (annotation instanceof com.softtech.kismiss.property.Property) {
//						Property property = (Property) annotation;
//						if (property.isInclude()) {
//							infos.add(new AttributeInfo(
//									MethodUtils.getFieldName(method.getName()), x, y, property.width(), 
//									property.height()==0?band.detailHeight():property.height(), property.key(), property.name(), property.position(), 
//									property.isStretchWithOverflow(), property.isBlankWhenNull(),property.evaluationTime(), property.hyperlinkType(), 
//									property.hyperlinkTarget(), property.fontSize(), property.font().toString(),property.leftPadding(), 
//									property.rightPadding(), property.topPadding(), property.bottomPadding(), property.lineWidth(), 
//									property.lineStyle().toString(), property.horizontalAlignment(), property.verticalAlignment(), property.abovePosition(), 
//									property.widthPortion(), property.heightPortion(), property.pattern(), MethodUtils.getReturnType(method.getReturnType().toString()), 
//									true)
//							);
//						}
//					}
//					else if (annotation instanceof com.softtech.kismiss.property.Calculation) {
//						com.softtech.kismiss.property.Calculation cal = (com.softtech.kismiss.property.Calculation) annotation;
//						Calculation calculation = new Calculation(
//								MethodUtils.getFieldName(method.getName()),
//								cal.calculationType().toString(), 0, 0, 0, 0 
//								);
//						cals.add(calculation);
//					}
//				}
//			}
//		}
//	}
	
}
