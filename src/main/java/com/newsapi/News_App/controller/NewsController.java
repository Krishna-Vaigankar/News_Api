package com.newsapi.News_App.controller;

import com.newsapi.News_App.service.FetchNewsService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/getNews")
public class NewsController {

    @Autowired
    FetchNewsService fetchNewsService;

    @PostMapping("/getNewsBy")
    public ResponseEntity<Object> getSourceName(@RequestBody JSONObject obj) {

        try {
            Map data = fetchNewsService.getNewsService(obj);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error while fetching news caused by: " + e.getCause() + " and message is: " + e.getMessage());
            return new ResponseEntity<>("Error while fetching news caused by: " + e.getCause() + " and message is: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}
