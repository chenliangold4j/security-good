package com.phantom5702.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Oauth {
    public static void main(String[] args) {
        SpringApplication.run(Oauth.class, args);
    }
}
