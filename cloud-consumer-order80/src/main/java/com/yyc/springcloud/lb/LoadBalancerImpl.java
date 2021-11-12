package com.yyc.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component //必须放到正确的路径，spring才能扫到
public class LoadBalancerImpl implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;

        } while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("------------第几次访问：次数next" + next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> instances) {
        int index = getIncrement() % instances.size();
        return instances.get(index);
    }

}
