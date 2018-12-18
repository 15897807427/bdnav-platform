package com.bdxh.message.api.model.enums;

/**
 * @description: 消息发送类型
 * @author: xuyuan
 * @create: 2018-12-17 14:57
 **/
public enum MqSendTypeEnum {
	/**
	 * 等待确认.
	 */
	WAIT_CONFIRM,

	/**
	 * 直接发送.
	 */
	SAVE_AND_SEND,

	/**
	 * 直接发送
	 */
	DIRECT_SEND;
}
