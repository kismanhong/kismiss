package com.softtech.kismiss.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * simplify report generation
 * ReportModel is used for sending all the informations that template need
 * this is used for java 1.5 above
 */
public class ReportModel {
	
	/**
	 * this variable infos<AttributeInfo> is for declaring of the column name,
	 * attribute, width/height of the column, strechable,... use in column
	 * header and detail most
	 */
	private List<PropertyInfo> infos = new ArrayList<PropertyInfo>();

	/**
	 * this variable designs<DesignInfo> is for declaring information of the
	 * design such as : background image(size, position...) 
	 */
	private List<DesignInfo> designs = new ArrayList<DesignInfo>();

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
	 * this reportParams use for declaring the begin of the jasper e.g:
	 * pageWidth, pageHeight, and the others 
	 */
	private Map<String, String> reportParams = new HashMap<String, String>();

	/**
	 * information of band params 
	 */
	private Map<String, Integer> headerParams = new HashMap<String, Integer>();

	/**
	 * information of the calculations
	 */
	private List<CalculationInfo> calculations = new ArrayList<CalculationInfo>();
	
//	private List<AttributeInfo> empties = new ArrayList<AttributeInfo>();
	
	private int position = 0;

	public Map<String, Integer> getHeaderParams() {
		return headerParams;
	}

	public void setHeaderParams(Map<String, Integer> headerParams) {
		this.headerParams = headerParams;
	}

	public List<CalculationInfo> getCalculations() {
		return calculations;
	}

	public void setCalculations(List<CalculationInfo> calculations) {
		this.calculations = calculations;
	}

	public List<DesignInfo> getDesigns() {
		return designs;
	}

	public void setDesigns(List<DesignInfo> designs) {
		this.designs = designs;
	}

	public Map<String, String> getreportParams() {
		return reportParams;
	}

	public void setreportParams(Map<String, String> reportParams) {
		this.reportParams = reportParams;
	}

	public List<PropertyInfo> getInfos() {
		return infos;
	}

	public void setInfos(List<PropertyInfo> infos) {
		this.infos = infos;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
//	public void putConstraint(String attributeName, int x, int y, int width,
//			int height, String key, String label, int column, String isOverFlow) {
//		//		if(!Load.getInstance().loadAttributeInfo(attributeName, getMethods()));
//		infos.add(new AttributeInfo(attributeName, x, y, width, height, key,
//				label, column, isOverFlow));
//	}
//
//	public void putConstraint(String attributeName, int width, int height,
//			String key, String label, int column, String isOverFlow) {
//
//		infos.add(new AttributeInfo(attributeName, x, y, width, height, key,
//				label, column, isOverFlow));
//		x = x + width;
//	}

}
