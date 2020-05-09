package com.yanzhen.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StreamReceiveMain8802 {
    public static void main(String[] args) {
        SpringApplication.run(StreamReceiveMain8802.class,args);
    }
}
