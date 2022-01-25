package com.feng.springcloud.controller;

import com.feng.springcloud.entities.CommonResult;
import com.feng.springcloud.entities.Payment;
import com.feng.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Mr.Feng
 * @date 11/30/2021 10:46 PM
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*****插入结果：" + result);

        if (result > 0){
            return new CommonResult(200, "插入数据成功,serverPort:" + serverPort, result);
        }else {
            return new CommonResult(404,"插入数据失败,serverPort:" + serverPort, null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****插入结果：" + payment);

        if (payment != null){
            return new CommonResult(200, "查询成功,serverPort" + serverPort, payment);
        }else {
            return new CommonResult(404,"查询失败ID,serverPort" + serverPort + id, null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}
