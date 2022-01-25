package com.feng.springcloud.service.impl;

import com.feng.springcloud.dao.PaymentDao;
import com.feng.springcloud.entities.Payment;
import com.feng.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Mr.Feng
 * @date 11/30/2021 10:45 PM
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
