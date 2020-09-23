package com.qishui.exception;

/**
 * 
 * @author zhou
 *
 */
public class AllException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 处理异常
	 * 
	 * @param e
	 */
	public static void handle(Exception e) {
		throw new RuntimeException(e);
	}

}
