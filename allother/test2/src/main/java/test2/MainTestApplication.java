package test2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MainTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainTestApplication.class, args);
    }

}
