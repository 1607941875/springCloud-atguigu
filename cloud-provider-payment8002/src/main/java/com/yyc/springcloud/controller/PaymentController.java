package com.yyc.springcloud.controller;


import com.yyc.springcloud.entities.CommonResult;
import com.yyc.springcloud.entities.Payment;
import com.yyc.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Payment)表控制层
 *
 * @author makejava
 * @since 2020-03-06 14:22:26
 */
@RestController
@RequestMapping("payment")
@Slf4j
public class PaymentController {
//    Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    /**
     * 服务对象
     */
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;
//    @Resource
//    private DiscoveryClient discoveryClient;
//

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("get/{id}")
    public CommonResult<Payment> selectOne(@PathVariable("id") Long id) {
        Payment result = this.paymentService.queryById(id);
        if (result != null) {
            log.info("查询成功" + result);
            int i = 1 + 1;
            System.out.println(i);
            return new CommonResult<Payment>(200, "查询成功,端口号是"+serverPort, result);
        } else return new CommonResult<Payment>(444, "查询失败", null);
    }

    //
    @PostMapping("create")
    public CommonResult create(@RequestBody Payment payment) {


//        System.out.println("进入create请求");
//        System.out.println(payment.getId());
//        System.out.println(payment.getSerial());

        Integer result = this.paymentService.insert(payment);

        if (result > 0) {
            log.info("插入成功" + result);
            return new CommonResult(200, "插入成功,端口号是"+serverPort, result);
        } else return new CommonResult(444, "插入失败", null);

    }
//
//    @GetMapping("discovery")
//    public Object discovery() {
//        List<String> services = discoveryClient.getServices();
//        services.forEach(service->{
//            System.out.println("----service"+service);
//        });
//        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
//        for (ServiceInstance instance : instances) {
//            System.out.println(instance.getServiceId()+"\t" + instance.getHost()+"\t"+ instance.getPort()+"\t"+instance.getUri());;
//        }
//
//        return this.discoveryClient;
//    }
//
    @GetMapping("lb")
    public String getPaymentLB() {
        return serverPort;
    }
//
//    @GetMapping("feign/timeout")
//    public String getFeignTimeOut() {
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return serverPort;
//    }
//
//    @GetMapping("zipkin")
//    public String paymentZipkin() {
//        return "hi,i`am paymentzipkin server fall back.welcome to atguigu.hahahahahhahahah";
//    }
}