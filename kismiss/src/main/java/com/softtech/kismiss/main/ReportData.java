package com.softtech.kismiss.main;

/**
 * @author Kisman Hong
 *
 */
public class ReportData {
	private int percentage;
	private String label;
	private int value;
	
	public ReportData(int percentage, String label, int value)
	{
		setLabel(label);
		setPercentage(percentage);
		setValue(value);
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
