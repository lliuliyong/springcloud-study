package com.leo.hello.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author liyong
 * @className HelloController
 * @date 2018/12/19
 */
@RestController
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DiscoveryClient client;
    @Value("${spring.application.name}")
    private String serviceName;

    @RequestMapping("/hello")
    public String index() throws InterruptedException {
        List<ServiceInstance> serviceInstances = client.getInstances(serviceName);
        client.getServices().forEach(s -> {
            logger.info("/hello,service:{}", s);
        });
        serviceInstances.forEach(serviceInstance -> {
            logger.info("/hello, host:{},serviceId:{},port:{}", serviceInstance.getHost(), serviceInstance.getServiceId(), serviceInstance.getPort());
        });
        int sleepTime = new Random().nextInt(3000);
        logger.info("sleepTime:" + sleepTime);
        TimeUnit.SECONDS.sleep(sleepTime);
        return "Hello World";
    }
}
