package com.yanzhen.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StreamReceiveMain8803 {
    public static void main(String[] args) {
        SpringApplication.run(StreamReceiveMain8803.class,args);
    }

}
