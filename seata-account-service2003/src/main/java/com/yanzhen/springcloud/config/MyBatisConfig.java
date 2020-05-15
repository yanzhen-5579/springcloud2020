package com.yanzhen.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.yanzhen.springcloud.alibaba.dao"})
public class MyBatisConfig {
}
