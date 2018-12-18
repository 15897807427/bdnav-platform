package com.bdxh.message.api.model.enums;

/**
 * @description: 消息顺序类型
 * @author: xuyuan
 * @create: 2018-12-17 14:57
 **/
public enum MqOrderTypeEnum {
	/**
	 * 无序.
	 */
	DIS_ORDER(0),
	/**
	 * 有序.
	 */
	ORDER(1);

	MqOrderTypeEnum(final int orderType) {
		this.orderType = orderType;
	}

	int orderType;

	public int orderType() {
		return orderType;
	}
}
