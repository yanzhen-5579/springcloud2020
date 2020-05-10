package com.yanzhen.springcloud.controller;



import com.yanzhen.springcloud.entities.CommenResult;
import com.yanzhen.springcloud.entities.Payment;
import com.yanzhen.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommenResult create(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        log.info("插入数据"+i);
        if(i > 0){
            return new CommenResult(200,"插入数据成功"+serverPort,i);
        }else {
            return new CommenResult(444,"插入数据失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommenResult getPaymentById(@PathVariable("id")Long id){
        Payment payment= paymentService.getPaymentById(id);
        log.info("查询数据"+ payment);
        if(payment != null){
            return new CommenResult(200,"查询成功"+serverPort,payment);
        }else {
            return new CommenResult(444,"查询失败",null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for(String str : services){
            log.info("****element"+str);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance serviceInstance: instances) {
            log.info(serviceInstance.getServiceId()+"\t"+serviceInstance.getHost()+"\t"+serviceInstance.getPort());
        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String paymentLb(){
        return this.serverPort;
    }

    @GetMapping("/payment/timeout")
    public String timeout() throws InterruptedException {
        Thread.sleep(3000);
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String zipkin(){
        return  "zipkin server";
    }
}
