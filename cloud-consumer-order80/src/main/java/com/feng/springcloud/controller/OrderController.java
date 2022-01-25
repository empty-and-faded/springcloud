package com.feng.springcloud.controller;

import com.feng.springcloud.entities.CommonResult;
import com.feng.springcloud.entities.Payment;
import com.feng.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author Mr.Feng
 * @date 11/30/2021 11:04 PM
 */
@RestController
@Slf4j
public class OrderController {
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    /**
     * LoadBalance源码分析
     *      @Resource
     *      private LoadBalancer loadBalancer;
     *
     *      @Resource
     *      private DiscoveryClient discoveryClient;
     *
     *      @GetMapping("/consumer/payment/lb")
     *      public String getPaymentLB(){
     *         List<ServiceInstance> instances = discoveryClient.getInstances("ClOUD-PAYMENT-SERVICE");
     *         if (instances == null || instances.size() <= 0){
     *             return null;
     *         }
     *         ServiceInstance serviceInstance = loadBalancer.instances(instances);
     *         URI uri = serviceInstance.getUri();
     *         return restTemplate.getForObject(uri + "/payment/lb", String.class);
     *      }
     */
    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }
}
