package com.bdxh.common.config.properties;

import lombok.Data;

/**
 * @description: 配置类
 * @author: xuyuan
 * @create: 2018-12-17 14:25
 **/
@Data
public class QiniuProperties {
	private QiniuKeyProperties key = new QiniuKeyProperties();
	private QiniuOssProperties oss = new QiniuOssProperties();

	@Data
	public class QiniuKeyProperties {
		private String accessKey;
		private String secretKey;
	}

	@Data
	public class QiniuOssProperties {
		private String privateHost;
		private String publicHost;
		private Long fileMaxSize;
	}
}
