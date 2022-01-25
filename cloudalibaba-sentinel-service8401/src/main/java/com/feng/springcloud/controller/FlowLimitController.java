package com.feng.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.feng.springcloud.entities.CommonResult;
import com.feng.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.Feng
 * @date 1/18/2022 7:31 PM
 */
@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "testB";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "handler_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2){
        return "-----testHotKey";
    }
    public String handler_testHotKey(String p1, String p2, BlockException exception){
        return "-----handler_testHotKey";
    }

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handler_byResource")
    public CommonResult byResource(){
        return new CommonResult(200, "按资源名称限流", new Payment(2020L, "serial001"));
    }
    public CommonResult handler_byResource(BlockException exception){
        return new CommonResult(444, exception.getClass().getCanonicalName() + "\t 服务不可用");
    }
}
