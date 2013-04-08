package com.softtech.kismiss.main;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softtech.kismiss.constant.CommonConstant;
import com.softtech.kismiss.constant.PdfReportParam;
import com.softtech.kismiss.constant.ReportFactory;
import com.softtech.kismiss.core.KismissFrontCore;
import com.softtech.kismiss.exception.KismissRuntimeException;
import com.softtech.kismiss.model.CalculationInfo;
import com.softtech.kismiss.model.DesignInfo;
import com.softtech.kismiss.model.GroupInfo;
import com.softtech.kismiss.model.PropertyInfo;
import com.softtech.kismiss.model.StyleInfo;
import com.softtech.kismiss.model.SubTitleInformation;

/**
 * @author Kisman Hong 
 * @email kismanhong@gmail.com
 * Softtech Inc. Dream Company
 * simplify report generation, AbstractKismissReport is
 *         used for getting all the information that send by
 *         code(attributes,which will be printed on report) this is used for
 *         java 1.5 above
 * 
 */
public class KismissReportInstance extends KismissFrontCore {

	/**
	 * define log variable
	 */
	private final static Logger logger = LoggerFactory.getLogger(KismissReportInstance.class);
	/**
	 * this is for declaring name of report, if it's not concerned
	 */
	private static final String DEFAULT_FILE_NAME = "reports";

	/**
	 * variable x is for declaring the x position or horizontal position of the
	 * report
	 */
	private int x = 0;

	/**
	 * variable y is for declaring the y position or vertical position of the
	 * report
	 */
	@SuppressWarnings("unused")
	private int y = 0;

	/**
	 * variable for detecting position
	 */
	private int position = 0;
	
	/**
	 * ResourceBundle for read kismiss properties file
	 */
	private ResourceBundle resourceBundle = null;
	
	/**
	 * jrxml output path
	 */
	private String OUTPUT_URL_PATH = "";
	
	/**
	 * log level to show jrxml result
	 */
	private String LOG_LEVEL = "";
	
	private SubTitleInformation subTitle = new SubTitleInformation();
	
	/**
	 * cancel the group in any situation
	 */
	private boolean groupVisible = true;

	/**
	 * constructor
	 */
	public KismissReportInstance() {		
		try {
		//	close();
			resourceBundle = ResourceBundle.getBundle(CommonConstant.KISMISS_PROPERTIES);				
			if (resourceBundle.getString(CommonConstant.OUTPUT_URL_PATH) != null) {
				OUTPUT_URL_PATH = resourceBundle.getString(CommonConstant.OUTPUT_URL_PATH);
				LOG_LEVEL = resourceBundle.getString(CommonConstant.LOG_LEVEL);
				/* will be implemented in the next release
				if(StringUtils.isNotEmpty(resourceBundle.getString(CommonConstant.LOCALE_PROVIDER))){
					LocaleConstant localeConstant = new LocaleConstant();
					localeConstant.setLocaleProvider(new Struts2LocaleProvider());
				}*/
			}
		} catch (Exception e) {
			logger.info("[Kismiss] kismiss.properties is not configured, jrxml file will not be created!");
			// no kismiss.properties configure, bypass and no jrxml file will be produced
		}		
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public void viewReport(Class clazz, List dataList, HashMap<String, Object> params, 
			com.softtech.kismiss.access.Group...groupObjects) throws KismissRuntimeException {
		try {
			List<PropertyInfo> infos = new ArrayList<PropertyInfo>();
			List<PropertyInfo> columnHeaders = new ArrayList<PropertyInfo>();
			
			Map<String, Object> reportParams = new HashMap<String, Object>();
			Map<String, Object> headerParams = new HashMap<String, Object>();
			
			List<GroupInfo> groups = new ArrayList<GroupInfo>();
			List<CalculationInfo> calculations = new ArrayList<CalculationInfo>();
			
			List<StyleInfo> styles = new ArrayList<StyleInfo>();
			
			Map<Object, Object> helperMap = new HashMap<Object, Object>();
			helperMap.put(CommonConstant.GROUP_VISIBLE, groupVisible);
			putAnnotatedData(clazz, infos, columnHeaders, reportParams, headerParams, groups, calculations, styles, helperMap, groupObjects);
				generateByJasperViewer(clazz, dataList, params, groups, infos, columnHeaders, dataList,
						reportParams, headerParams, calculations, styles, helperMap);
			close();
		} catch (Exception e) {
			logger.error("Failed generate view report caused by : {}", e.toString());
			throw new KismissRuntimeException(e.toString());
		}

	}

	/**
	 * @param clazz
	 * @param dataList
	 * @param destination
	 * @param params
	 * @throws KismissRuntimeException
	 * function for generating excel file report
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	public void generateAnnotatedXlsFiles(Class clazz, List dataList, String destination, HashMap<String, Object> params, 
			com.softtech.kismiss.access.Group...groupObjects) throws KismissRuntimeException {
		try {
			List<PropertyInfo> infos = new ArrayList<PropertyInfo>();
			List<PropertyInfo> columnHeaders = new ArrayList<PropertyInfo>();
			
			Map<String, Object> reportParams = new HashMap<String, Object>();
			Map<String, Object> headerParams = new HashMap<String, Object>();
			
			List<GroupInfo> groups = new ArrayList<GroupInfo>();
			List<CalculationInfo> calculations = new ArrayList<CalculationInfo>();
			
			List<StyleInfo> styles = new ArrayList<StyleInfo>();
			
			Map<Object, Object> helperMap = new HashMap<Object, Object>();
			helperMap.put(CommonConstant.GROUP_VISIBLE, groupVisible);
			putAnnotatedData(clazz, infos, columnHeaders, reportParams, headerParams, groups, calculations, styles, helperMap, groupObjects);
			reportParams.put(ReportFactory.REPORT_TYPE, ReportFactory.REPORT_XLS);
				generateXlsReportsFile(clazz, dataList, destination, params, groups, infos, columnHeaders, dataList,
						reportParams, headerParams, calculations, styles, helperMap);
			close();
		} catch (Exception e) {
			logger.error("Failed generate xls caused by : {}", e.toString());
			throw new KismissRuntimeException(e.toString());
		}

	}

	/**
	 * @param clazz
	 * @param dataList
	 * @param destination
	 * @param params
	 * @throws KismissRuntimeException
	 * function for generating pdf file report
	 */
	@SuppressWarnings({"rawtypes"})
	public void generateAnnotatedPdfFiles(Class clazz, List dataList, String destination, HashMap<String, Object> params,
			com.softtech.kismiss.access.Group...groupObjects) throws KismissRuntimeException {
		try {
			List<PropertyInfo> infos = new ArrayList<PropertyInfo>();
			List<PropertyInfo> columnHeaders = new ArrayList<PropertyInfo>();
			
			Map<String, Object> reportParams = new HashMap<String, Object>();
			Map<String, Object> headerParams = new HashMap<String, Object>();
			
			List<GroupInfo> groups = new ArrayList<GroupInfo>();
			List<CalculationInfo> calculations = new ArrayList<CalculationInfo>();
			
			List<StyleInfo> styles = new ArrayList<StyleInfo>();
			
			Map<Object, Object> helperMap = new HashMap<Object, Object>();
			helperMap.put(CommonConstant.GROUP_VISIBLE, groupVisible);
			putAnnotatedData(clazz, infos, columnHeaders, reportParams, headerParams, groups, calculations, styles, helperMap, groupObjects);
				generatePdfReportsFile(clazz, dataList, destination, params, groups, infos, columnHeaders, new ArrayList<DesignInfo>(),
						reportParams, headerParams, calculations, styles, helperMap);
			close();
		} catch (Exception e) {
			logger.error("Failed generate pdf caused by : {}", e.toString());
			throw new KismissRuntimeException(e.toString());
		}

	}

	/**
	 * @param clazz
	 * @param dataList
	 * @param destination
	 * @param params
	 * @throws KismissRuntimeException
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	public void generateAnnotatedHtmlFiles(Class clazz, List dataList, String destination, HashMap<String, Object> params,
			com.softtech.kismiss.access.Group...groupObjects) throws KismissRuntimeException {
		try {
			List<PropertyInfo> infos = new ArrayList<PropertyInfo>();
			List<PropertyInfo> columnHeaders = new ArrayList<PropertyInfo>();
			
			Map<String, Object> reportParams = new HashMap<String, Object>();
			Map<String, Object> headerParams = new HashMap<String, Object>();
			
			List<GroupInfo> groups = new ArrayList<GroupInfo>();
			List<CalculationInfo> calculations = new ArrayList<CalculationInfo>();
			
			List<StyleInfo> styles = new ArrayList<StyleInfo>();
			
			Map<Object, Object> helperMap = new HashMap<Object, Object>();
			helperMap.put(CommonConstant.GROUP_VISIBLE, groupVisible);
			putAnnotatedData(clazz, infos, columnHeaders, reportParams, headerParams, groups, calculations, styles, helperMap, groupObjects);
//			try {
				generateHtmlReportsFile(clazz, dataList, destination, params, groups, infos, columnHeaders, dataList,
						reportParams, headerParams, calculations, styles, helperMap);
//			} catch (Exception e) {
//				logger.info("Error happened, action canceled by user");
//			}
//			close();
		} catch (Exception e) {
			logger.error("Failed generate html caused by : {}", e.toString());
			throw new KismissRuntimeException(e.toString());
		}

	}

	/**
	 * @param clazz
	 * @param dataList
	 * @param params
	 * @param response
	 * @throws KismissRuntimeException
	 * method used for generate XLS file in browser
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	public void generateAnnotatedStreamXls(Class clazz, List dataList, HashMap<String, Object> params, HttpServletResponse response, 
			com.softtech.kismiss.access.Group...groupObjects) throws KismissRuntimeException {
		try {
			List<PropertyInfo> infos = new ArrayList<PropertyInfo>();
			List<PropertyInfo> columnHeaders = new ArrayList<PropertyInfo>();
			
			Map<String, Object> reportParams = new HashMap<String, Object>();
			Map<String, Object> headerParams = new HashMap<String, Object>();
			
			List<GroupInfo> groups = new ArrayList<GroupInfo>();
			List<CalculationInfo> calculations = new ArrayList<CalculationInfo>();
			
			List<StyleInfo> styles = new ArrayList<StyleInfo>();
			
			Map<Object, Object> helperMap = new HashMap<Object, Object>();
			helperMap.put(CommonConstant.GROUP_VISIBLE, groupVisible);		
			putAnnotatedData(clazz, infos, columnHeaders, reportParams, headerParams, groups, calculations, styles, helperMap, groupObjects);			
			reportParams.put(ReportFactory.REPORT_TYPE, ReportFactory.REPORT_XLS);
			
//			try {
				generateStreamXlsReports(clazz, dataList, params, response, groups, infos, columnHeaders, dataList,
						reportParams, headerParams, calculations, styles, helperMap);
//			} catch (Exception e) {
//				logger.info("Error happened, action canceled by user");
//			}
			close();
		} catch (Exception e) {
			logger.error("Failed generate stream xls caused by : {}", e.toString());
			throw new KismissRuntimeException(e.toString());
		}

	}

	/**
	 * @param clazz
	 * @param dataList
	 * @param params
	 * @param response
	 * @throws KismissRuntimeException
	 * method used for generate PDF file in browser
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	public void generateAnnotatedStreamPdf(Class clazz, List dataList, HashMap<String, Object> params, HttpServletResponse response,
			com.softtech.kismiss.access.Group...groupObjects) throws KismissRuntimeException {
		try {
			
			List<PropertyInfo> infos = new ArrayList<PropertyInfo>();
			List<PropertyInfo> columnHeaders = new ArrayList<PropertyInfo>();
			
			Map<String, Object> reportParams = new HashMap<String, Object>();
			Map<String, Object> headerParams = new HashMap<String, Object>();
			
			List<GroupInfo> groups = new ArrayList<GroupInfo>();
			List<CalculationInfo> calculations = new ArrayList<CalculationInfo>();
			
			List<StyleInfo> styles = new ArrayList<StyleInfo>();		
			
//			Kismiss kismiss = getKismiss(clazz);
//			if(kismiss.isCache() && isFileExists(clazz)){
//				
//			}else{			
				Map<Object, Object> helperMap = new HashMap<Object, Object>();
				helperMap.put(CommonConstant.GROUP_VISIBLE, groupVisible);
				putAnnotatedData(clazz, infos, columnHeaders, reportParams, headerParams, groups, calculations, styles, helperMap, groupObjects);
//				try {
					generateStreamPdfReports(clazz, dataList, params, response, groups, infos, columnHeaders, dataList,
							reportParams, headerParams, calculations, styles, helperMap);
//				} catch (Exception e) {
//					logger.info("Error happened, action canceled by user");
//				}
				close();
//			}
		} catch (Exception e) {
			logger.error("Failed generate stream pdf caused by : {}", e.toString());
			throw new KismissRuntimeException(e.toString());
		}

	}

	/**
	 * @param clazz
	 * @param dataList
	 * @param params
	 * @param response
	 * @throws KismissRuntimeException
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	public void generateAnnotatedStreamHtml(Class clazz, List dataList, HashMap<String, Object> params, HttpServletResponse response,
			HttpServletRequest request, com.softtech.kismiss.access.Group...groupObjects) throws KismissRuntimeException {
		try {
			List<PropertyInfo> infos = new ArrayList<PropertyInfo>();
			List<PropertyInfo> columnHeaders = new ArrayList<PropertyInfo>();
			
			Map<String, Object> reportParams = new HashMap<String, Object>();
			Map<String, Object> headerParams = new HashMap<String, Object>();
			
			List<GroupInfo> groups = new ArrayList<GroupInfo>();
			List<CalculationInfo> calculations = new ArrayList<CalculationInfo>();
			
			List<StyleInfo> styles = new ArrayList<StyleInfo>();
			
			Map<Object, Object> helperMap = new HashMap<Object, Object>();
			helperMap.put(CommonConstant.GROUP_VISIBLE, groupVisible);
			putAnnotatedData(clazz, infos, columnHeaders, reportParams, headerParams, groups, calculations, styles, helperMap, groupObjects);
//			try {
				generateHtmlStreamReports(clazz, dataList, params, response, request, groups, infos, columnHeaders, dataList, styles,
						reportParams, headerParams, calculations,  helperMap);
//			} catch (Exception e) {
//				logger.info("Error happened, action canceled by user");
//			}
			close();
		} catch (Exception e) {
			logger.error("Failed generate stream html caused by : {}", e.toString());
			throw new KismissRuntimeException(e.toString());
		}

	}

	/**
	 * clear all collections, int value
	 */
	public void close() {
		x = 0;
		y = 0;
		position = 0;
		subTitle = new SubTitleInformation();
		setVisibleField(null);
		setGroupVisible(true);
	}

	/**
	 * @return
	 * specify the empty column, width and height
	 */
	private List<PropertyInfo> emptySpace(List<PropertyInfo> infos, List<CalculationInfo> calculations, Map<String, String> others) {	
		List<PropertyInfo> empty = new ArrayList<PropertyInfo>();
		int calTitlePosition = titlePosition(infos, calculations)[0];
		others.put("summaryPosition", new Integer(calTitlePosition).toString());
		position = titlePosition(infos, calculations)[1];
		
		for (int index = position; index < infos.size(); index++) {
			if (calculations.size() == 0){
				break;
			}
			boolean check = false;
			String bgColor = "";
			PropertyInfo emptyInfo = (PropertyInfo) infos.get(index);
			if(emptyInfo.isShowInDetail()){
				for (CalculationInfo cal : calculations) {
					if (cal.getCalName().startsWith(emptyInfo.getAttributeName())){
						check = true;	
					}
					bgColor = cal.getBackColor();
				}
				emptyInfo.setX(calTitlePosition);
				emptyInfo.setBgColor(bgColor);
				calTitlePosition += emptyInfo.getWidth();
	
				if (!check){
					empty.add(emptyInfo);
				}
			}
		}
		return empty;
	}

	/**
	 * @return int
	 * positioning title
	 */
	private int[] titlePosition(List<PropertyInfo> infos, List<CalculationInfo> calculations) {
		int[] positions = new int[2];		
		for (PropertyInfo info : infos) {
			if (calculations.size() == 0 || calculations.get(0).getCalName().startsWith(info.getAttributeName())){
				break;
			}
			if(info.isShowInDetail()){
				positions[0] += info.getWidth();			
			}
			positions[1]++;
		}
		return positions;
	}
	
	/**
	 * @param templateName
	 * @param params
	 * @param groups
	 * @param infos
	 * @param columnHeaders
	 * @param designs
	 * @param reportParams
	 * @param headerParams
	 * @param calculations
	 * @param helperMap
	 * @return merge the report template with the report information
	 * @throws Exception
	 */
	private byte[] mergeTemplateToStream(String templateName, HashMap<String, Object> params, 
			List<GroupInfo> groups, List<PropertyInfo> infos, List<PropertyInfo> columnHeaders, List<DesignInfo> designs,
			Map<String, Object> reportParams, Map<String, Object> headerParams, List<CalculationInfo> calculations, 
			List<StyleInfo> styles,	Map<Object, Object> helperMap) throws Exception {
		Properties props = new Properties();
		props.setProperty("resource.loader", "file");
		props.setProperty("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Velocity.init(props);
		
		reportParams.put("finalWidth", getFinalWidth());

		VelocityContext context = new VelocityContext();
		Template template = Velocity.getTemplate(helperMap.get("ReportType").toString()+".vm");
		StringWriter writer = new StringWriter();
		Map<String, String> others = new HashMap<String, String>();
		others.put("allWidth", new Integer(x).toString());
		
			context.put("groups", groups);
			context.put("infos", infos);
			context.put("columnHeaders", columnHeaders);
			context.put("designs", designs);
			context.put("reportParams", reportParams);
			context.put("headerParams", headerParams);
			context.put("calculations", calculations);
			context.put("empty", emptySpace(infos, calculations, others));
			context.put("others", others);
			context.put("subTitles", params.get(ReportFactory.SUB_TITLE));
			context.put("subTitleInfo", subTitle);
			context.put("columnFooters", params.get(ReportFactory.COLUMN_FOOTER));
			context.put("styles", styles);
			template.merge(context, writer);

		InputStream inputStream = new ByteArrayInputStream(writer.toString().getBytes());
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		
		StringBuffer buffer = new StringBuffer();
		String strLine;
		// Read File Line By Line
		while ((strLine = br.readLine()) != null) {
			// Print the content on the console
//			System.out.println(strLine);
			buffer.append(strLine + "\n");
		}
		inputStream.close();
		String result = buffer.toString();
		
		if(StringUtils.isNotEmpty(OUTPUT_URL_PATH)){
			String path = OUTPUT_URL_PATH + (params.get(ReportFactory.REPORT_NAME) == null ? ReportFactory.DEFAULT_REPORT_NAME 
					: params.get(ReportFactory.REPORT_NAME).toString());
			path = StringUtils.replace(path, ".pdf", ".jrxml");
			path = StringUtils.replace(path, ".xls", ".jrxml");
			File outFile = new File(path);
            FileWriter out = new FileWriter(outFile);
            out.write(result);
            out.close();
		}
		//detect log level from kismiss.properties
		if("debug".equalsIgnoreCase(LOG_LEVEL)){
			logger.debug("JRXML Report Template : \n {}", result);
		}else if("info".equalsIgnoreCase(LOG_LEVEL)){
			logger.info ("JRXML Report Template : \n {}", result);
		}else if("warn".equalsIgnoreCase(LOG_LEVEL)){
			logger.warn ("JRXML Report Template : \n {}", result);
		}else if("console".equalsIgnoreCase(LOG_LEVEL)){
			System.out.println("JRXML Report Template : \n "+ result);
		}else if(StringUtils.isBlank(LOG_LEVEL)){
			logger.info ("[KISMISS] JRXML log level is not configured!");
		}else{
			logger.info ("[KISMISS] No kismiss.logger.level value at kismiss.properties : {}", LOG_LEVEL);
		}
		
		return result.getBytes();
	}
	
//	private byte[] mergeTemplateToStream(String templateName, HashMap<String, Object> params, 
//			List<GroupInfo> groups, List<PropertyInfo> infos, List<PropertyInfo> columnHeaders, List<DesignInfo> designs,
//			Map<String, Object> reportParams, Map<String, Object> headerParams, List<CalculationInfo> calculations, 
//			List<StyleInfo> styles,	Map<Object, Object> helperMap, boolean isCache, String location) throws Exception {
//		Properties props = new Properties();
//		props.setProperty("resource.loader", "file");
//		props.setProperty("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
//		Velocity.init(props);
//		
//		reportParams.put("finalWidth", getFinalWidth());
//
//		VelocityContext context = new VelocityContext();
//		Template template = Velocity.getTemplate(helperMap.get("ReportType").toString()+".vm");
//		StringWriter writer = new StringWriter();
//		Map<String, String> others = new HashMap<String, String>();
//		others.put("allWidth", new Integer(x).toString());
//		
//			context.put("groups", groups);
//			context.put("infos", infos);
//			context.put("columnHeaders", columnHeaders);
//			context.put("designs", designs);
//			context.put("reportParams", reportParams);
//			context.put("headerParams", headerParams);
//			context.put("calculations", calculations);
//			context.put("empty", emptySpace(infos, calculations, others));
//			context.put("others", others);
//			context.put("subTitles", params.get(ReportFactory.SUB_TITLE));
//			context.put("subTitleInfo", subTitle);
//			context.put("columnFooters", params.get(ReportFactory.COLUMN_FOOTER));
//			context.put("styles", styles);
//			template.merge(context, writer);
//
//		InputStream inputStream = new ByteArrayInputStream(writer.toString().getBytes());
//		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//		
//		StringBuffer buffer = new StringBuffer();
//		String strLine;
//		// Read File Line By Line
//		while ((strLine = br.readLine()) != null) {
//			// Print the content on the console
//			//System.out.println(strLine);
//			buffer.append(strLine + "\n");
//		}
//		inputStream.close();
//		String result = buffer.toString();
//		
//		if(StringUtils.isNotEmpty(OUTPUT_URL_PATH)){
//			String path = OUTPUT_URL_PATH + (params.get(ReportFactory.REPORT_NAME) == null ? ReportFactory.DEFAULT_REPORT_NAME 
//					: params.get(ReportFactory.REPORT_NAME).toString());
//			path = StringUtils.replace(path, ".pdf", ".jrxml");
//			path = StringUtils.replace(path, ".xls", ".jrxml");
//			File outFile = new File(path);
//            FileWriter out = new FileWriter(outFile);
//            out.write(result);
//            out.close();
//		}
//		//detect log level from kismiss.properties
//		if("debug".equalsIgnoreCase(LOG_LEVEL)){
//			logger.debug("JRXML Report Template : \n {}", result);
//		}else if("info".equalsIgnoreCase(LOG_LEVEL)){
//			logger.info ("JRXML Report Template : \n {}", result);
//		}else if("warn".equalsIgnoreCase(LOG_LEVEL)){
//			logger.warn ("JRXML Report Template : \n {}", result);
//		}else if("error".equalsIgnoreCase(LOG_LEVEL)){
//			logger.error("JRXML Report Template : \n {}", result);
//		}else if("console".equalsIgnoreCase(LOG_LEVEL)){
//			System.out.println("JRXML Report Template : \n "+ result);
//		}else if(StringUtils.isBlank(LOG_LEVEL)){
//			logger.info ("[KISMISS] JRXML log level is not configured!");
//		}else{
//			logger.warn ("[KISMISS] Invalid kismiss.logger.level value at kismiss.properties : {}", LOG_LEVEL);
//		}
//		
//		return result.getBytes();
//	}

	/**
	 * @param object
	 * @param dataList
	 * @param params
	 * @param response
	 * @param request
	 * @throws Exception
	 *             generating Html file stream mode
	 */
	private void generateHtmlStreamReports(Class<?> clazz, List<?> dataList, HashMap<String, Object> params, HttpServletResponse response,
			HttpServletRequest request,
			List<GroupInfo> groups, List<PropertyInfo> infos, List<PropertyInfo> columnHeaders, List<DesignInfo> designs, List<StyleInfo> styles,
			Map<String, Object> reportParams, Map<String, Object> headerParams, List<CalculationInfo> calculations, 
			Map<Object, Object> helperMap) throws Exception { // yang dikirim
		byte[] bytes = mergeTemplateToStream(DEFAULT_FILE_NAME, params, groups, infos, columnHeaders, designs, 
				reportParams, headerParams, calculations, styles, helperMap );
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		
		JasperDesign design = JRXmlLoader.load(bais);
		JasperReport report = JasperCompileManager.compileReport(design);
		bais.close();

		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataList);
		JasperPrint jPrint = JasperFillManager.fillReport(report, params, ds);
		
		generateHtmlOutput(jPrint, response, request);

	}

	/**
	 * @param clazz
	 * @param dataList
	 * @param destination
	 * @param params
	 * @throws Exception
	 */
	private void generateHtmlReportsFile(Class<?> clazz, List<?> dataList, String destination, HashMap<String, Object> params,
			List<GroupInfo> groups, List<PropertyInfo> infos, List<PropertyInfo> columnHeaders, List<DesignInfo> designs,
			Map<String, Object> reportParams, Map<String, Object> headerParams, List<CalculationInfo> calculations, List<StyleInfo> styles,
			Map<Object, Object> helperMap) throws Exception {
		String path = params.get(ReportFactory.REPORT_NAME)== null?ReportFactory.DEFAULT_HTML_NAME:params.get(ReportFactory.REPORT_NAME).toString();
		byte[] bytes = mergeTemplateToStream(DEFAULT_FILE_NAME, params, groups, infos, columnHeaders, designs, 
				reportParams, headerParams, calculations, styles, helperMap);
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		
		JasperDesign design = JRXmlLoader.load(bais);
		JasperReport report = JasperCompileManager.compileReport(design);
		bais.close();

		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataList);
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, ds);
		JRHtmlExporter exporter = new JRHtmlExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		try{
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destination + path);
		} catch (Exception e) {
			throw new KismissRuntimeException(e);
		}
		exporter.exportReport();
	}

	/**
	 * @param clazz
	 * @param dataList
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	private void generateStreamPdfReports(Class<?> clazz, List<?> dataList, HashMap<String, Object> params, HttpServletResponse response,
			List<GroupInfo> groups, List<PropertyInfo> infos, List<PropertyInfo> columnHeaders, List<DesignInfo> designs,
			Map<String, Object> reportParams, Map<String, Object> headerParams, List<CalculationInfo> calculations, List<StyleInfo> styles,
			Map<Object, Object> helperMap)
			throws Exception {
		byte[] bytes = mergeTemplateToStream(DEFAULT_FILE_NAME, params, groups, infos, columnHeaders, designs, 
				reportParams, headerParams, calculations, styles, helperMap);

		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		
		
//		JasperReport jasperReport = JasperCompileManager.compileReport(bais);
//		bais.close();
//		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataList);
//
//		byte[] _pdf = null;
//
//		_pdf = JasperRunManager.runReportToPdf(jasperReport, params, ds);
//		response.setContentType("application/pdf");
//		response.setContentLength(_pdf.length);
//		response.setBufferSize(_pdf.length);
//		response.getOutputStream().write(_pdf);
		
		JasperDesign design = JRXmlLoader.load(bais);
		JasperReport report = JasperCompileManager.compileReport(design);
		bais.close();

		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataList);
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, ds);
		String reportfilename = params.get(ReportFactory.REPORT_NAME) + ".pdf";
		JRPdfExporter exporterPDF = new JRPdfExporter();
		exporterPDF.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
		
		for (Iterator<?> iterator = params.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			try{
				PdfReportParam.class.getDeclaredField(key);
				Field field = JRPdfExporterParameter.class.getDeclaredField(key);
				field.setAccessible(true);
				exporterPDF.setParameter((JRExporterParameter)field.get(JRPdfExporterParameter.class), params.get(key));
			}catch (Exception e) {
				// PDF Param will only valid in report param
			}
		}

		exporterPDF.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, response.getOutputStream());
		response.setHeader("Content-Disposition", "inline;filename=" + reportfilename);
		response.setContentType("application/pdf");
		exporterPDF.exportReport();
		response.getOutputStream().close();
		
	}

	/**
	 * @param clazz
	 * @param dataList
	 * @param destination
	 * @param params
	 * @throws Exception
	 */
	private void generatePdfReportsFile(Class<?> clazz, List<?> dataList, String destination, HashMap<String, Object> params,
			List<GroupInfo> groups, List<PropertyInfo> infos, List<PropertyInfo> columnHeaders, List<DesignInfo> designs,
			Map<String, Object> reportParams, Map<String, Object> headerParams, List<CalculationInfo> calculations, List<StyleInfo> styles,
			Map<Object, Object> helperMap) throws Exception {
		String path = params.get(ReportFactory.REPORT_NAME)== null?ReportFactory.DEFAULT_PDF_NAME:params.get(ReportFactory.REPORT_NAME).toString();
		byte[] bytes = mergeTemplateToStream(DEFAULT_FILE_NAME, params, groups, infos, columnHeaders, designs, 
				reportParams, headerParams, calculations, styles, helperMap);
		
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		
		JasperDesign design = JRXmlLoader.load(bais);
		JasperReport report = JasperCompileManager.compileReport(design);
		bais.close();
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataList);
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, ds);
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
		try {
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destination + path);		
			for (Iterator<?> iterator = params.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				try{
					PdfReportParam.class.getDeclaredField(key);
					Field field = JRPdfExporterParameter.class.getDeclaredField(key);
					field.setAccessible(true);
					exporter.setParameter((JRExporterParameter)field.get(JRPdfExporterParameter.class), params.get(key));
				}catch (Exception e) {
					// PDF Param will only valid in report param
				}
			}
			exporter.exportReport();
		} catch (Exception e) {
			logger.error("Unknown error : {}", e.toString());
			throw new KismissRuntimeException(e);
		}

	}

	/**
	 * @param clazz
	 * @param dataList
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	private void generateStreamXlsReports(Class<?> clazz, List<?> dataList, HashMap<String, Object> params, HttpServletResponse response,
			List<GroupInfo> groups, List<PropertyInfo> infos, List<PropertyInfo> columnHeaders, List<DesignInfo> designs,
			Map<String, Object> reportParams, Map<String, Object> headerParams, List<CalculationInfo> calculations, List<StyleInfo> styles,
			Map<Object, Object> helperMap) throws Exception {
		String reportName = params.get(ReportFactory.REPORT_NAME)== null?ReportFactory.DEFAULT_XLS_NAME:params.get(ReportFactory.REPORT_NAME).toString();
		
		byte[] bytes = mergeTemplateToStream(DEFAULT_FILE_NAME, params, groups, infos, columnHeaders, designs,
				reportParams, headerParams, calculations, styles, helperMap);

		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		
		JasperDesign design = JRXmlLoader.load(bais);
		JasperReport report = JasperCompileManager.compileReport(design);
		bais.close();

		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataList);

		JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, ds);
		
		generateXLSOutput(reportName, jasperPrint, response, params);

	}

	/**
	 * @param object
	 * @param dataList
	 * @param destination
	 * @param params
	 * @param groups
	 * @param infos
	 * @param columnHeaders
	 * @param designs
	 * @param reportParams
	 * @param headerParams
	 * @param calculations
	 * @param helperMap
	 * @throws Exception
	 */
	private void generateXlsReportsFile(Object object, List<?> dataList, String destination, HashMap<String, Object> params, 
			List<GroupInfo> groups, List<PropertyInfo> infos, List<PropertyInfo> columnHeaders, List<DesignInfo> designs,
			Map<String, Object> reportParams, Map<String, Object> headerParams, List<CalculationInfo> calculations, List<StyleInfo> styles,
			Map<Object, Object> helperMap) throws Exception {	
		
		String path = params.get(ReportFactory.REPORT_NAME)== null?ReportFactory.DEFAULT_XLS_NAME:params.get(ReportFactory.REPORT_NAME).toString();
		byte[] bytes = mergeTemplateToStream(DEFAULT_FILE_NAME, params, groups, infos, columnHeaders, designs, 
				reportParams, headerParams, calculations, styles, helperMap);
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		
		JasperDesign design = JRXmlLoader.load(bais);
		JasperReport report = JasperCompileManager.compileReport(design);
		bais.close();
		
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataList);
		JasperPrint jasperPrint = JasperFillManager.fillReport(report,params, ds);

		JRXlsExporter xlsExporter = new JRXlsExporter();
		try {
			xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destination + path);
		} catch (Exception e) {
			logger.error("Failed generate xls caused by : {}", e.toString());
			throw new KismissRuntimeException(e);
		}
		
		xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		xlsExporter.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
		xlsExporter.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
		xlsExporter.setParameter(JExcelApiExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
		xlsExporter.setParameter(JExcelApiExporterParameter.IGNORE_PAGE_MARGINS, Boolean.FALSE);
		xlsExporter.setParameter(JExcelApiExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		xlsExporter.setParameter(JExcelApiExporterParameter.IS_IGNORE_GRAPHICS,Boolean.TRUE);
		for (Iterator<?> iterator = params.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			try{
				PdfReportParam.class.getDeclaredField(key);
				Field field = JRXlsExporterParameter.class.getDeclaredField(key);
				field.setAccessible(true);
				xlsExporter.setParameter((JRExporterParameter)field.get(JRPdfExporterParameter.class), params.get(key));
			}catch (Exception e) {
				// XLS Param will only valid in report param
			}
		}
		xlsExporter.exportReport();
	}
	
	private void generateByJasperViewer(Object object, List<?> dataList, HashMap<String, Object> params, 
			List<GroupInfo> groups, List<PropertyInfo> infos, List<PropertyInfo> columnHeaders, List<DesignInfo> designs,
			Map<String, Object> reportParams, Map<String, Object> headerParams, List<CalculationInfo> calculations, List<StyleInfo> styles,
			Map<Object, Object> helperMap) throws Exception {	
		byte[] bytes = mergeTemplateToStream(DEFAULT_FILE_NAME, params, groups, infos, columnHeaders, designs, 
				reportParams, headerParams, calculations, styles, helperMap);
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		
		JasperDesign design = JRXmlLoader.load(bais);
		JasperReport report = JasperCompileManager.compileReport(design);
		bais.close();
		
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataList);
		JasperPrint jasperPrint = JasperFillManager.fillReport(report,params, ds);
		
        JasperViewer.viewReport(jasperPrint);
	}
	
/*	private void generatePdfReport(HttpServletResponse response, 
			String reportPdfName, Map<String, Object> parameters, List<?> data, String reportFile) throws Exception{
			JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream(reportFile));
			ServletOutputStream servletOutputStream = response.getOutputStream();
		    byte[] bytes = JasperRunManager.runReportToPdf( report, parameters, new JRBeanCollectionDataSource(data));
		    	
		    response.setContentType("application/pdf");
		    response.setHeader("Content-Disposition", "inline; filename=report.pdf");
		    response.setContentLength(bytes.length);
		        
		    servletOutputStream.write(bytes, 0, bytes.length);
		    servletOutputStream.flush();
		    servletOutputStream.close();               
	} */
	
//	private Kismiss getKismiss(Class<?> clazz){
//		Annotation[] annotations = clazz.getAnnotations();
//		for (Annotation annotation : annotations) {
//			if(annotation instanceof Kismiss){
//				return ((Kismiss) annotation);
//			}
//		}
//		return null;
//	}

//	private boolean isFileExists(Class<?> clazz) throws KismissException{
//		File file = new File(ClassUtils.getFileLocation(clazz));
//		return file.exists();
//	}
	
/*	private boolean isFileExists(String path) throws KismissException{
		String realPath = StringUtils.replace(path, "." , "/");
		try {
			getClass().getResourceAsStream(realPath);
			return true;
		} catch (Exception e) {
			logger.info("[KISMISS] template report not exists, a new one will be created at the first compile!");
		}
		return false;
	} */
	
	/**
	 * @param clazz
	 * @return
	 */
/*	private CacheInfo getCacheInfo(Class<?> clazz){
		Annotation[] annotations = clazz.getAnnotations();
		CacheInfo cacheInfo = null;
		for (Annotation annotation : annotations) {
			if(annotation instanceof Cache){
				Cache cache = (Cache) annotation;
				cacheInfo = new CacheInfo(cache.name(), cache.cacheStrategyType(), cache.relativePath());
				return cacheInfo;
			}
		}
		return null;
	} */
	
	public void setVisibleField(String[] fieldNames) {
		setFieldNames(fieldNames);
	}

	public void setSubTitleFont(String fontName) {
		subTitle.setFontName(fontName);
	}

	public void setSubTitleFontSize(int size) {
		subTitle.setFontSize(size);
	}

	public void setSubTitleLeftPadding(int leftPadding) {
		subTitle.setLeftPadding(leftPadding);
	}

	public void setSubTitleRightPadding(int rightPadding) {
		subTitle.setRightPadding(rightPadding);
	}
	
	public void setSubTitleHeight(int height){
		subTitle.setHeight(height);
	}

	public void setGroupVisible(boolean groupVisible) {
		this.groupVisible = groupVisible;
	}
	
}
