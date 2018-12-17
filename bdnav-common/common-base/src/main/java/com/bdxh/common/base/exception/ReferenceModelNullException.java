package com.bdxh.common.base.exception;

/**
 * @description: 空指针异常
 * @author: xuyuan
 * @create: 2018-12-17 14:25
 **/
public class ReferenceModelNullException extends RuntimeException {

	private static final long serialVersionUID = -318154770875589045L;

	public ReferenceModelNullException(String message) {
		super(message);
	}
}
