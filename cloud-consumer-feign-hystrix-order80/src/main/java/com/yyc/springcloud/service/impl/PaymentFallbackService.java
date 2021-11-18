package com.yyc.springcloud.service.impl;

import com.yyc.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "我是 PaymentFallbackService 的 paymentInfo_OK ";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "我是 PaymentFallbackService 的 paymentInfo_TimeOut ";
    }
}
