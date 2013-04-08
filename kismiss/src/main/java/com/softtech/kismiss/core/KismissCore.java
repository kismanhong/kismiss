package com.softtech.kismiss.core;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import com.softtech.kismiss.constant.PdfReportParam;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * Report processing and genarating
 */
public abstract class KismissCore {
	
	private final static transient String IMAGE_PATH = "src/main/java/";
	
	protected void generateXLSOutput(String reportName, JasperPrint jasperPrint, HttpServletResponse resp, 
			HashMap<String, Object> params) throws IOException, JRException {
		String reportfilename = (reportName) + ".xls";
		JRXlsExporter exporterXLS = new JRXlsExporter();
		exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
		// exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.TRUE);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.FALSE);
		exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, resp.getOutputStream());
		for (Iterator<?> iterator = params.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			try{
				PdfReportParam.class.getDeclaredField(key);
				Field field = JRXlsExporterParameter.class.getDeclaredField(key);
				field.setAccessible(true);
				exporterXLS.setParameter((JRExporterParameter)field.get(JRPdfExporterParameter.class), params.get(key));
			}catch (Exception e) {
				// Xls Param will only valid in report param
			}
		}
		resp.setHeader("Content-Disposition", "inline;filename=" + reportfilename);
		resp.setContentType("application/vnd.ms-excel");

		exporterXLS.exportReport();
		resp.getOutputStream().close();
		// clear();
	}
	
	protected void generateHtmlOutput(JasperPrint jasperPrint,
			HttpServletResponse resp, HttpServletRequest request)
			throws IOException, JRException {

		// resp.setContentType("text/html");
		// PrintWriter out = resp.getWriter();
		// JRExporter exporter = new JRHtmlExporter();
		// // Map imagesMap = new HashMap();
		// // request.getSession().setAttribute("IMAGES_MAP", imagesMap);
		// exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		// exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
		// //
		// exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
		// // Boolean.FALSE);
		// exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
		// "../images/pximages/");
		// exporter.exportReport();
		// out.flush();
		// out.close();
		// clear();

		File imageFile = new File("");
		System.out.println("images path :" + imageFile.getAbsolutePath());
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		// String pathName = request.getRealPath("report/" + "reportHtml"
		// + ".html");
		// String i =
		// "http://"+request.getLocalAddr()+":"+Integer.toString(request.getLocalPort())+request.getContextPath()+"\\LHBUWeb\\WebContent\\fixedreport\\internal.jpg";
		// String i =
		// "http://"+request.getLocalAddr()+":"+Integer.toString(request.getLocalPort())+request.getContextPath()+"\\images\\internal.jpg";
		StringBuffer sbHeader = new StringBuffer();
		sbHeader
				.append("<head>")
				.append("<title></title>")
				.append(
						"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>")
				.append("<style type=\"text/css\">")
				// .append("table {").append("background-image:
				// url(").append(url).append(");").append("}")

				// .append("table {").append("background-image:
				// url(file://").append(i).append(");").append("}")
				.append(
						"\n body { margin:50px 0px; padding:0px; text-align:center; } ")
				.append("table {").append("background-image: url(..").append(
						"../img/refresh.png").append(");").append(
						"\nbackground-repeat: no-repeat;").append(
						"\nbackground-position:center;").append(
						"\nmargin-left : auto;").append("margin-right : auto;")
				.append("}")
				// ok .append("table {").append("background-image:
				// url(../fixedreport/internal.jpg").append(");").append("}")
				// .append("table {").append("background-image:
				// url(file:///").append(url).append(");").append("}")

				// file:///
				.append("</style>").append("</head>");
		// String a = request.getContextPath();
		// String b = request.getPathInfo();
		// String c = request.getProtocol();
		// String d = request.getLocalAddr();
		// String e = request.getRequestURL().toString();
		// String f = Integer.toString(request.getLocalPort());
		// System.out.println(sbHeader.toString());

		/*
		 * <head> <title></title> <meta http-equiv="Content-Type"
		 * content="text/html; charset=UTF-8"/> <style type="text/css"> table {
		 * background-image:
		 * url(../../../../Project%20LHBU/Support/watermark.jpg); } </style>
		 * </head>
		 */

		JRHtmlExporter exporter = new JRHtmlExporter();
		// JasperExportManager.exportReportToHtmlFile(jasperPrint, pathName);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		// exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,pathName);
		// if (!isChart)
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
		// if (isChart)
		// {

		// exporter.setParameter(JRHtmlExporterParameter.OUTPUT_STRING_BUFFER,new
		// StringBuffer());
		// exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,"../images/pximages/");
		exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, sbHeader
				.toString());
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
				IMAGE_PATH);

		exporter.exportReport();
		out.flush();
		out.close();
	}
	
}
