/*
 * Copyright 2014 guilin. All rights reserved.
 * Support: guilin
 * License: guilin
 */
package gov.guilin;

/**
 * 公共参数
 * 
 * @author guilin
 * @version
 */
public final class CommonAttributes {

	/** 日期格式配比 */
	public static final String[] DATE_PATTERNS = new String[] { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };

	/** 文件路径 */
	public static final String XML_PATH = "/guilin.xml";

	/** properties文件路径 */
	public static final String PROPERTIES_PATH = "/guilin.properties";

	/**
	 * 不可实例化
	 */
	private CommonAttributes() {
	}

}