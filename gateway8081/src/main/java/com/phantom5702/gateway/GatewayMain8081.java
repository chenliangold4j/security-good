package com.phantom5702.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayMain8081 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain8081.class);
    }
}
