package com.anqi.alldemo.demoboottest.goods.controller;

import com.anqi.alldemo.demoboottest.goods.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("getItems")
    public String getItems() {
        List<String> items = testService.getItems();
        return "success-"+"-"+items;
    }
}
