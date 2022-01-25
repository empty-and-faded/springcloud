package com.feng.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Mr.Feng
 * @date 1/2/2022 1:25 PM
 */
@Service
public class PaymentService {
    public String paymentInfo_success(Integer id){
        return Thread.currentThread().getName() + " success id:" + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_timeout(Integer id){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        int i = 10/0;
        return Thread.currentThread().getName() + " timeout id:" + id;
    }
    public String paymentInfo_timeoutHandler(Integer id){
        return Thread.currentThread().getName() + " paymentInfo_timeoutHandler id:" + id;
    }
}
