package com.softtech.kismiss.exception;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * simplify report generation
 * KismissException is used for handling the exception by code
 * this is used for java 1.5 above
 * 
 */
public class KismissException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3224929220568773428L;

    /**
     * error/exception code
     */
    private String code;

    /**
     * error description value/s
     */
    private Object[] parameterValues = null;

    /**
     * default constructor
     */
    public KismissException() {
    }


    /**
     * @param message
     * constructor for accepting message
     */
    public KismissException(String message) {
        super(message);
        setCode(message);
    }

    /**
     * @param message
     * @param code
     * constructor for accepting message and code
     */
    public KismissException(String message, String code) {
        super(message);
        setCode(code);
    }


    /**
     * @param message
     * @param code
     * @param params
     * constructor for accepting message, code, and params
     */
    public KismissException(String message, String code, Object[] params) {
        super(message);
        setCode(code);
        setParameterValues(params);
    }

    /**
     * @param cause
     */
    public KismissException(Throwable cause) {
        super(cause);
        if (cause instanceof KismissException) {
            setCode(((KismissException) cause).getCode());
            setParameterValues(((KismissException) cause).getParameterValues());
        }
    }

    /**
     * @param message
     * @param cause
     */
    public KismissException(String message, Throwable cause) {
        super(message, cause);
        setCode(message);
    }

    /**
     * @param code
     * @param params
     * @param cause
     */
    public KismissException(String code, Object[] params, Throwable cause) {
        super(cause);
        setCode(code);
        setParameterValues(params);
    }

    /**
     * @param code
     * @param param
     * @param cause
     */
    public KismissException(String code, Object param, Throwable cause) {
        this(code, new Object[]{param}, cause);
    }
    
    /**
     * @param code
     * @param params
     */
    public KismissException(String code, Object[] params) {
//        super(ErrorMessages.global.getMessage(code, params));
        setCode(code);
        setParameterValues(params);
    }

    /**
     * @param code
     * @param param
     */
    public KismissException(String code, Object param) {
        this(code, new Object[]{param});
    }
 
    /**
     * @return
     */
    public String getKismissRootMessage() {
        Throwable cause = this;
        while (cause.getCause() != null && cause.getCause() instanceof KismissException) {
            cause = cause.getCause();
        }
        return ((KismissException) cause).getLocalizedMessage();
    }

	/**
	 * @return
	 */
	public String getRootMessage() {
		Throwable cause = this;
		while (cause.getCause() != null) {
			cause = cause.getCause();
		}
		return cause.getLocalizedMessage();
	}
	
	/**
     * @return
     */
    public String getCode() {
        return code;
    }


    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return
     */
    public Object[] getParameterValues() {
        return parameterValues;
    }

    /**
     * @param objects
     */
    public void setParameterValues(Object[] objects) {
        parameterValues = objects;
    }
}
