package com.leo.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author liuliyong
 * @className HelloService
 * @date 2018/12/24
 */
@Service
public class HelloService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService() {
        long start = System.currentTimeMillis();
        String res = restTemplate.getForObject("http://HELLO-SERVICE/hello", String.class);
        logger.info("Spend Time : {}", (System.currentTimeMillis() - start));
        return res;
    }

    public String helloFallback() {
        return "error";
    }
}
