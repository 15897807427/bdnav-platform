package com.bdxh.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

/**
 * @description:
 * @author: xuyuan
 * @create: 2018-12-16 13:21
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableTurbineStream
public class TurbineServer {
    public static void main(String[] args) {
        SpringApplication.run(TurbineServer.class,args);
    }
}
