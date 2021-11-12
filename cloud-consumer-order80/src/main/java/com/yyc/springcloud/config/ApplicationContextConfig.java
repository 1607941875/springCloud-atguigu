package com.yyc.springcloud.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration

public class ApplicationContextConfig {

    @Bean
//    @LoadBalanced
    public RestTemplate getRestTemplate() {

        return new RestTemplate();

    }
    @LoadBalancerClients(defaultConfiguration = CustomLoadBalancerConfiguration.class)
    public class CustomLoadBalancerConfiguration {
        @Bean
        ReactorLoadBalancer<ServiceInstance> roundRobinLoadBalancer(Environment environment, LoadBalancerClientFactory loadBalancerClientFactory){
            String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
            return new RoundRobinLoadBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
            //RoundRobinLoadBalancer 轮询
            //RandomLoadBalancer 随机
        }
    }


}
