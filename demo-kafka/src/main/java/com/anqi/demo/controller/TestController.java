package com.anqi.demo.controller;

import com.anqi.demo.producer.DemoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private DemoProducer demoProducer;

    @GetMapping("/send")
    public void sendMsg(){
        demoProducer.send("首次测试");
    }
}
