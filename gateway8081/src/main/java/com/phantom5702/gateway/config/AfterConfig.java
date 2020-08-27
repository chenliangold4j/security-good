package com.phantom5702.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.config.LoadBalancerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class AfterConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    LoadBalancerClient loadBalancer;

    @Autowired
    LoadBalancerProperties properties;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("------------refresh--------------");
    }

}
