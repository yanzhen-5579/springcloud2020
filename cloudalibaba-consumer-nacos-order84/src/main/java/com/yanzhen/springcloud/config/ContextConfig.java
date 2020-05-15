package com.yanzhen.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ContextConfig {
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
         return new RestTemplate();
    }
}