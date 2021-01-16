package com.anqi.demo.demoasync.controller;

import com.anqi.demo.demoasync.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncTestController {

    @Autowired
    OrderService orderService;

    @GetMapping("testAsync")
    public String testAsync() {
        orderService.createOrder("ooo");
        return "success";
    }
}
