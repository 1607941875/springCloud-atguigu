package com.yyc.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("consumer")
public class OrderConsulController {

    @Resource
    private RestTemplate restTemplate;
    public static final String INVOKE_URL="http://consul-payment-service";

    @GetMapping("payment/consul")
    public String getPaymentInfo() {
        String info = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
        return info;
    }

}
