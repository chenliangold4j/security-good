package com.phantom5702.zuul.config;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.client.utils.JSONUtils;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.Map;

@Slf4j
public class MyRule extends ZoneAvoidanceRule {

    @Override
    public Server choose(Object key) {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if (request.getHeader("test") != null) {
            ILoadBalancer loadBalancer = getLoadBalancer();
            List<Server> reachableServers = loadBalancer.getReachableServers();
            for (Server server : reachableServers) {
                NacosServer nacosServer = (NacosServer) server;
                Map<String, String> metadata = nacosServer.getMetadata();
                if (metadata.containsKey("test")) {
                    return server;
                }
            }
        }
        return super.choose(key);
    }
}