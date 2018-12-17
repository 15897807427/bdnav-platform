package com.bdxh.common.config.properties;

import lombok.Data;

/**
 * @description: 配置类
 * @author: xuyuan
 * @create: 2018-12-17 14:25
 **/
@Data
public class AsyncTaskProperties {

	private int corePoolSize = 50;

	private int maxPoolSize = 100;

	private int queueCapacity = 10000;

	private int keepAliveSeconds = 3000;

	private String threadNamePrefix = "bdnav-task-executor-";
}
