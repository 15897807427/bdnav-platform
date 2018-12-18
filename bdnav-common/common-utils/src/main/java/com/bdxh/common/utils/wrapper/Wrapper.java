package com.bdxh.common.utils.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * @description: 通用返回类
 * @author: xuyuan
 * @create: 2018-12-17 14:57
 **/
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Wrapper<T> implements Serializable {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = 4893280118017319089L;

	/**
	 * 成功码.
	 */
	public static final int SUCCESS_CODE = 200;

	/**
	 * 成功信息.
	 */
	public static final String SUCCESS_MESSAGE = "操作成功";

	/**
	 * 错误码.
	 */
	public static final int ERROR_CODE = 500;

	/**
	 * 错误信息.
	 */
	public static final String ERROR_MESSAGE = "内部异常";

	/**
	 * 错误码：参数非法
	 */
	public static final int ILLEGAL_ARGUMENT_CODE_ = 100;

	/**
	 * 错误信息：参数非法
	 */
	public static final String ILLEGAL_ARGUMENT_MESSAGE = "参数非法";

	/**
	 * 编号.
	 */
	private int code;

	/**
	 * 信息.
	 */
	private String message;

	/**
	 * 结果数据
	 */
	private T result;


	Wrapper() {
		this(SUCCESS_CODE, SUCCESS_MESSAGE);
	}


	Wrapper(int code, String message) {
		this(code, message, null);
	}


	Wrapper(int code, String message, T result) {
		super();
		this.code(code).message(message).result(result);
	}

	private Wrapper<T> code(int code) {
		this.setCode(code);
		return this;
	}

	private Wrapper<T> message(String message) {
		this.setMessage(message);
		return this;
	}

	public Wrapper<T> result(T result) {
		this.setResult(result);
		return this;
	}

	/**
	 * 判断是否成功： 依据 Wrapper.SUCCESS_CODE == this.code
	 */
	@JsonIgnore
	public boolean success() {
		return Wrapper.SUCCESS_CODE == this.code;
	}

	/**
	 * 判断是否成功： 依据 Wrapper.SUCCESS_CODE != this.code
	 */
	@JsonIgnore
	public boolean error() {
		return !success();
	}

}
