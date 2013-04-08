package com.softtech.kismiss.exception;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * KismissRuntimeException is used for handling runtime exception
 *
 */
public class KismissRuntimeException extends KismissException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 151997398226701577L;

	/**
	 * default constructor
	 */
	public KismissRuntimeException() {
		super();
	}

	/**
	 * @param message
	 * accepting message parameter
	 */
	public KismissRuntimeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public KismissRuntimeException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public KismissRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param code
	 * @param params
	 * @param cause
	 */
	public KismissRuntimeException(String code, Object[] params,
			Throwable cause) {
		super(code, params, cause);
	}

	/**
	 * @param code
	 * @param param
	 * @param cause
	 */
	public KismissRuntimeException(String code, Object param, Throwable cause) {
		super(code, param, cause);
	}

	/**
	 * @param code
	 * @param params
	 */
	public KismissRuntimeException(String code, Object[] params) {
		super(code, params);
	}

	/**
	 * @param code
	 * @param param
	 */
	public KismissRuntimeException(String code, Object param) {
		super(code, param);
	}
}
