package com.feng.springcloud.controller;

import com.feng.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Mr.Feng
 * @date 1/2/2022 4:35 PM
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/hystrix/success/{id}")
    public String paymentInfo_success(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_success(id);
        log.info("*******result:" + result);

        return result;
    }

    @GetMapping("/consumer/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeoutFallbackMethod", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand
    public String paymentInfo_timeout(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_timeout(id);
        log.info("*******result:" + result);

        return result;
    }
    public String paymentTimeoutFallbackMethod(@PathVariable("id") Integer id) {
        return "consumer 80: paymentTimeoutFallbackMethod";
    }
    public String paymentGlobalFallbackMethod(){
        return "global 异常处理";
    }

}
