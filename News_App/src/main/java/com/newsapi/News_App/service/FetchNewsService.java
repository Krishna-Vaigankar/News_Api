package com.newsapi.News_App.service;

import com.newsapi.News_App.repository.FetchNewsRepo;
import com.newsapi.News_App.repositoryImpl.FetchNewsRepoImpl;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FetchNewsService {

    @Autowired
    private FetchNewsRepo fetchNewsRepo;

    public Map getNewsService(JSONObject obj) throws Exception {
        return fetchNewsRepo.getNewsRepo(obj);
    }
}
