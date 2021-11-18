package com.yyc.springcloud.controller;

import com.yyc.springcloud.entities.CommonResult;
import com.yyc.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yyc.springcloud.service.PaymentFeignService;

import javax.annotation.Resource;

@RequestMapping("consumer-feign")
@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;


    @GetMapping(value = "/payment/get/{id}")
    //@pathVariable 接收请求路径中占位符的值
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.selectOne(id);
    }

    @GetMapping("/payment/feign/timeout")
    public String getFeignTimeOut() {
        return paymentFeignService.getFeignTimeOut();
    }
}
