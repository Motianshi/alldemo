package com.anqi.alldemo.demoboottest.controller;

import com.anqi.alldemo.demoboottest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("getItems")
    public String getItems(String id) {
        List<String> items = testService.getItems();
        return "success-"+id+"-"+items;
    }
}
