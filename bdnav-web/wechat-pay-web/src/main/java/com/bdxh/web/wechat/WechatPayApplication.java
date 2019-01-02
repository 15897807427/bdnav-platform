package com.bdxh.web.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @description: 微信支付启动类
 * @author: xuyuan
 * @create: 2019-01-02 17:14
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WechatPayApplication {
    public static void main(String[] args) {
        SpringApplication.run(WechatPayApplication.class,args);
    }
}
