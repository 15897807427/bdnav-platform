package com.bdxh.task.api.exceptions;

import com.bdxh.common.base.enums.ErrorCodeEnum;
import com.bdxh.common.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 任务模块异常类
 * @author: xuyuan
 * @create: 2018-12-17 14:57
 **/
@Slf4j
public class TaskBizException extends BusinessException {

	private static final long serialVersionUID = -6552248511084911254L;

	public TaskBizException() {
	}

	public TaskBizException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
		log.info("<== TaskBizException, code:{}, message:{}", this.code, super.getMessage());
	}

	public TaskBizException(int code, String msg) {
		super(code, msg);
		log.info("<== TaskBizException, code:{}, message:{}", this.code, super.getMessage());
	}

	public TaskBizException(ErrorCodeEnum codeEnum) {
		super(codeEnum.getCode(), codeEnum.getMsg());
		log.info("<== TaskBizException, code:{}, message:{}", this.code, super.getMessage());
	}

	public TaskBizException(ErrorCodeEnum codeEnum, Object... args) {
		super(codeEnum, args);
		log.info("<== TaskBizException, code:{}, message:{}", this.code, super.getMessage());
	}
}
