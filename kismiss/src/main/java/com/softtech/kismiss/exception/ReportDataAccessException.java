package com.softtech.kismiss.exception;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * simplify report generation
 * ReportDataAccessException is used for showing exception 
 * when the data is not valid or the difined is not compatible the required
 * this is used for java 1.5 above
 *
 */
public class ReportDataAccessException extends KismissException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -589934615582386891L;


    /**
     * default constructor
     */
    public ReportDataAccessException() {
        super();
    }


    /**
     * @param message
     */
    public ReportDataAccessException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ReportDataAccessException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ReportDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param code
     * @param params
     * @param cause
     */
    public ReportDataAccessException(String code, Object[] params, Throwable cause) {
        super(code, params, cause);
    }

    /**
     * @param code
     * @param param
     * @param cause
     */
    public ReportDataAccessException(String code, Object param, Throwable cause) {
        super(code, param, cause);
    }

    /**
     * @param code
     * @param params
     */
    public ReportDataAccessException(String code, Object[] params) {
        super(code, params);
    }

    /**
     * @param code
     * @param param
     */
    public ReportDataAccessException(String code, Object param) {
        super(code, param);
    }
}
