package com.bdxh.provider.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description: 启动类
 * @author: xuyuan
 * @create: 2018-12-27 12:32
 **/
@SpringBootApplication
@EnableEurekaClient
public class DemoProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoProviderApplication.class,args);
    }
}
