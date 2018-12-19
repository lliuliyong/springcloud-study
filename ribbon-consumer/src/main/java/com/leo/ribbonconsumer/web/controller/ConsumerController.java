package com.leo.ribbonconsumer.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author liyong
 * @date 2018/12/19
 */
@RestController
public class ConsumerController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/ribbon-consumer")
    public String helloConsumer() {
        return restTemplate.getForObject("https://HELLO-SERVICE/hello", String.class);
    }
}
