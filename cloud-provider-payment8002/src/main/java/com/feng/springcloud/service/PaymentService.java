package com.feng.springcloud.service;

import com.feng.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author Mr.Feng
 * @date 11/30/2021 10:42 PM
 */
public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
