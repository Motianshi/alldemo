package com.anqi.alldemo.demoboottest.goods.service;

import com.anqi.alldemo.demoboottest.goods.dto.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService {

    @Autowired
    private ObjectMapper objectMapper;

    public List<String> getItems() {
        List<String> orders = Arrays.asList("a", "b", "c");
        return orders.stream().map(this::getItemInfo).collect(Collectors.toList());
    }

    public String getItemInfo(String id) {
        return id + "-info";
    }

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ProductDTO[] products =
                    objectMapper.readValue(ResourceUtils.getFile(("classpath:jsonfiles/productFile.json")), ProductDTO[].class);
            System.out.println(111);
            System.out.println(products[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
