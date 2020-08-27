package com.phantom5702.gateway.config;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.config.LoadBalancerProperties;
import org.springframework.cloud.gateway.filter.LoadBalancerClientFilter;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

public class MyLoadBalancerClientFilter extends LoadBalancerClientFilter {

    @Autowired
    SpringClientFactory clientFactory;

    public MyLoadBalancerClientFilter(LoadBalancerClient loadBalancer, LoadBalancerProperties properties) {
        super(loadBalancer, properties);
    }

    protected ServiceInstance choose(ServerWebExchange exchange) {
        if (exchange.getRequest().getHeaders().containsKey("test")) {
            List<Server> reachableServers = clientFactory.getLoadBalancer(((URI) exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR)).getHost()).getReachableServers();
            for (Server server : reachableServers) {
                NacosServer nacosServer = (NacosServer) server;
                Map<String, String> metadata = nacosServer.getMetadata();
                if (metadata.containsKey("test")) {
                    return new RibbonLoadBalancerClient.RibbonServer(((URI) exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR)).getHost(), server, false,
                            metadata);
                }
            }
        }

        return loadBalancer.choose(
                ((URI) exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR)).getHost());
    }
}
