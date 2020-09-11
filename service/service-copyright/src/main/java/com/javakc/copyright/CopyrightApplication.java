package com.javakc.copyright;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.javakc"})
@EnableDiscoveryClient
public class CopyrightApplication {
    public static void main(String[] args) {
        SpringApplication.run(CopyrightApplication.class,args);
    }
}
