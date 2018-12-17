package com.bdxh.common.config;

import com.bdxh.common.config.properties.GlobalProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 配置类
 * @author: xuyuan
 * @create: 2018-12-17 14:25
 **/
@Configuration
@EnableConfigurationProperties(GlobalProperties.class)
public class BdnavCoreConfig {
}
