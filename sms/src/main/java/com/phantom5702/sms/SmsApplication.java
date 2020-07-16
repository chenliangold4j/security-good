package com.phantom5702.sms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.phantom5702.sms.dao")
public class SmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class,args);
    }
}
