package com.softtech.kismiss.model;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * simplify report generation
 * DesignInfo is used for handling the design information 
 * from user(background,watermak,etc)
 * this is used for java 1.5 above
 */
public class DesignInfo {
	
	private String name;
	private int x;
	private int y;
	private int width;
	private int height;

	private String key;
	
	/**
	 * @param name
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param key
	 */
	public DesignInfo(String name,int x,int y, int width, int height, String key)
	{
		setName(name);
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setKey(key);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
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
}
