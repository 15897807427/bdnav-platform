package com.bdxh.common.utils.exception;


/**
 * @description: http异常类
 * @author: xuyuan
 * @create: 2018-12-17 14:25
 **/
public class HttpAesException extends IllegalArgumentException {
	private static final long serialVersionUID = -2489600753056921095L;

	public HttpAesException(String message) {
		super(message);
	}

	public HttpAesException() {
	}
}
