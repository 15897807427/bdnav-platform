package com.bdxh.message.api.model.enums;

/**
 * @description: 消息类型
 * @author: xuyuan
 * @create: 2018-12-17 14:57
 **/
public enum MqMessageTypeEnum {
	/**
	 * 生产者.
	 */
	PRODUCER_MESSAGE(10, "生产者"),
	/**
	 * 消费者.
	 */
	CONSUMER_MESSAGE(20, "消费者");

	private int messageType;

	private String value;

	MqMessageTypeEnum(int messageType, String value) {
		this.messageType = messageType;
		this.value = value;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
