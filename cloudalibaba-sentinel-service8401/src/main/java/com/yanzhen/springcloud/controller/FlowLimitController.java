package com.yanzhen.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testa")
    public String testa() throws InterruptedException {
        return "testa";
    }
    @GetMapping("/testb")
    public String testb(){
        log.info(Thread.currentThread().getName()+"testb");
        return "testb";
    }

    @GetMapping("/testd")
    public String testd() throws InterruptedException {
        int a = 10/0;
        return "testd";
    }
}
