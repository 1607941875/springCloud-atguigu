package com.yyc.springcloud.service;


import com.yyc.springcloud.entities.CommonResult;
import com.yyc.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {


    @GetMapping(value = "payment/get/{id}")
    //@pathVariable 接收请求路径中占位符的值
    public CommonResult<Payment> selectOne(@PathVariable("id") Long id);

    @GetMapping("payment/feign/timeout")
    public String getFeignTimeOut();
}

