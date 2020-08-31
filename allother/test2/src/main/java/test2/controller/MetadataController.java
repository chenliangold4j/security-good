package test2.controller;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/meta")
public class MetadataController {

    @Autowired
    NacosDiscoveryProperties nacosDiscoveryProperties;

    @GetMapping("/modify")
    public String modify(String value) {
        //这样修改是无效的。。只能看nacos的修改metadata接口
        Map<String, String> metadata = nacosDiscoveryProperties.getMetadata();
        metadata.put("test", value);
        return "ok";
    }
}
