package com.bdxh.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @description:
 * @author: xuyuan
 * @create: 2018-12-15 13:36
 **/
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class GatewayZuul2 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayZuul2.class,args);
    }
}
