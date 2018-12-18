package com.bdxh.task.api.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 任务实体
 * @author: xuyuan
 * @create: 2018-12-17 14:57
 **/
@Data
public class PcMessageJobTask implements Serializable {

	private static final long serialVersionUID = -1689940882253489536L;

	/**
	 * 自增ID
	 */
	private String id;

	/**
	 * 版本号
	 */
	private String version;

	/**
	 * 消息key
	 */
	private Long messageKey;

	/**
	 * topic
	 */
	private String messageTopic;

	/**
	 * tag
	 */
	private String messageTag;
}