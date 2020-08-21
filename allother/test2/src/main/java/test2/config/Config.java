package test2.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;

@Configuration
public class Config {

    static{
        System.out.println("初始化");
    }

    @Autowired
    NacosDiscoveryProperties nacosDiscoveryProperties;

    @PostConstruct
    public void modify() {
        System.out.println("modify");
        Map<String, String> metadata = nacosDiscoveryProperties.getMetadata();
        metadata.put("test","helloWorld");
    }

}
