package com.bdxh.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @description:
 * @author: xuyuan
 * @create: 2018-12-16 12:56
 **/
@SpringBootApplication
@EnableHystrixDashboard
@EnableEurekaClient
public class HystrixDashboardServer {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardServer.class,args);
    }
}
