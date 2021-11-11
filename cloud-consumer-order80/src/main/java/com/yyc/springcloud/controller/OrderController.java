package com.yyc.springcloud.controller;

import com.yyc.springcloud.entities.CommonResult;
import com.yyc.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

//        public static final String PAYMENT_URL = "http://127.0.0.1:8002";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id) {

        log.info("拦截到请求，参数id的值为" + id);
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> creat(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResult<Object> getPayment2(@PathVariable("id") Long id) {
        ResponseEntity responseEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        System.out.println("code====");
        System.out.print(responseEntity.getStatusCode());
        System.out.println("body====");
        System.out.print(responseEntity.getBody());
        System.out.println("header====");
        System.out.print(responseEntity.getHeaders());
        return new CommonResult<Object>(200, "消费者查询成功", responseEntity.getBody());
    }


}
