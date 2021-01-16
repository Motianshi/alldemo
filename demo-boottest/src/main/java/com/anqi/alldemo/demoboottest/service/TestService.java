package com.anqi.alldemo.demoboottest.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TestService {

    public List<String> getItems() {
        return Arrays.asList("a","b","c");
    }
}
