package com.bdxh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description: 启动类
 * @author: xuyuan
 * @create: 2018-12-27 17:46
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@ComponentScan({"com.bdxh.web","com.bdxh.provider.api"})
public class DemoWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoWebApplication.class,args);
    }
}
