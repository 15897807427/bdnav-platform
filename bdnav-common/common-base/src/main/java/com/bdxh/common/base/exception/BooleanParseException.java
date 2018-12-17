package com.bdxh.common.base.exception;

/**
 * @description: boolean转换异常
 * @author: xuyuan
 * @create: 2018-12-17 14:25
 **/
public class BooleanParseException extends RuntimeException {

	public BooleanParseException() {
		super();
	}

	public BooleanParseException(String message) {
		super(message);
	}

	public BooleanParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BooleanParseException(Throwable cause) {
		super(cause);
	}

}
