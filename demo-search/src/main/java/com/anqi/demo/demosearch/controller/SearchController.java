package com.anqi.demo.demosearch.controller;

import com.anqi.demo.demosearch.service.RestHighLevelClientService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SearchController {

    @Autowired
    RestHighLevelClientService service;

    @RequestMapping("/search")
    public String search() {
        SearchHits hits = null;
        try {
            SearchResponse search = service.search("name", "s", "idx_s");
            hits = search.getHits();
            Object[] collapseValues = hits.getCollapseValues();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  hits.toString();
    }

}
