package com.yyc.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service

public class PaymentService {

    /**
     * @description: 方法描述 模拟正常信息
     * @author: yyc
     * @date: 2021/11/13
     * @params:
     * @return:
     */
    public String paymentInfo_OK(Integer id) {

        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_OK,id:" + id;
    }

    /**
     * @description: 超时异常
     * @author: yyc
     * @date: 2021/11/13
     * @params:
     * @return:
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandle", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id) {

        int timeNumber = 2;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        int i = 1 / 0;
        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_TimeOut,id:" + id + "耗时" + timeNumber + "秒";
    }

    public String paymentInfo_TimeOutHandle(Integer id) {

        return "进入兜底的处理方法，线程池:" + Thread.currentThread().getName() + " paymentInfo_OK,id:" + id;
    }

    //------------------服务的熔断
    @HystrixCommand(
            fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),//失败率达到多少后跳闸
    }
    )
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        //自定义一个不能为负数的异常
        if (id < 0) {
            throw new RuntimeException("******id不能为负数");
        }
        String simpleUUID = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "成功调用，流水号是：" + simpleUUID;
    }

    //paymentCircuitBreaker 的兜底函数
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id不能为负数，请稍后再试............" + id;
    }


}
