package com.yanzhen.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/testhotkey")
    @SentinelResource(value = "testhotkey",blockHandler = "deal_hotkey")
    public String testHotkey(@RequestParam(value = "p1",required = false) String p1,@RequestParam(value = "p2",required = false) String p2){
        return "testhotkey";
    }
    public String deal_hotkey(String p1, String p2, BlockException exception){
        return "deal_hotkey";
    }
}
