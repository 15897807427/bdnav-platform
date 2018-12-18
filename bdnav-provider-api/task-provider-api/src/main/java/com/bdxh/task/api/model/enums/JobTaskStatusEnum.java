package com.bdxh.task.api.model.enums;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @description: 任务状态
 * @author: xuyuan
 * @create: 2018-12-17 14:57
 **/
public enum JobTaskStatusEnum {

	/**
	 * 创建任务数据  状态码0
	 */
	TASK_CREATE(0, "创建任务数据"),

	/**
	 * 等待处理 状态码1
	 */
	TASK_WAITING(1, "等待处理"),

	/**
	 * 正在处理中  状态码2
	 */
	TASK_EXETING(2, "正在处理中"),

	/**
	 * 处理成功
	 */
	TASK_SUCCESS(3, "处理成功"),

	/**
	 * 任务异常
	 */
	TASK_FAIL(4, "处理失败"),;

	int status;

	String value;

	JobTaskStatusEnum(int status, String value) {
		this.status = status;
		this.value = value;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private static List<Integer> getStatusList() {
		List<Integer> list = Lists.newArrayList();
		for (JobTaskStatusEnum ele : JobTaskStatusEnum.values()) {
			list.add(ele.getStatus());
		}
		return list;
	}

	public static boolean contains(Integer status) {
		List<Integer> statusList = getStatusList();
		return statusList.contains(status);
	}

}
