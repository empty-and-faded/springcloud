package com.feng.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Feng
 * @date 1/2/2022 6:07 PM
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_success(Integer id) {
        return "-----PaymentFallbackService paymentInfo_success";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "-----PaymentFallbackService paymentInfo_timeout";
    }
}
