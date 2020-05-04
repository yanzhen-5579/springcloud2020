package com.yanzhen.springcloud.controller;

import com.netflix.discovery.converters.Auto;
import com.yanzhen.springcloud.entities.CommenResult;
import com.yanzhen.springcloud.entities.Payment;
import com.yanzhen.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    LoadBalancer loadBalancer;



    @GetMapping("/consumer/payment/create")
    public CommenResult<Payment> create(Payment payment){
         return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommenResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommenResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommenResult.class);
    }
    @GetMapping("/consumner/payment/getforentity/{id}")
    public CommenResult<Payment> getForEntity(@PathVariable("id") Long id){
        ResponseEntity<CommenResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommenResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommenResult<Payment>(444,"错误");
        }
    }
    @GetMapping("/consumer/payment/lb")
    public String paymentlb(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null || instances.size() < 1){
            return  null;
        }
        ServiceInstance instance = loadBalancer.instances(instances);
        URI uri = instance.getUri();
        return  restTemplate.getForObject(uri+"/payment/lb",String.class);
    }
}
