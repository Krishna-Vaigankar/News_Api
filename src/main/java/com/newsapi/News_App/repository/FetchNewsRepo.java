package com.newsapi.News_App.repository;

import org.json.simple.JSONObject;
import java.util.Map;

public interface FetchNewsRepo {
    Map getNewsRepo(JSONObject obj) throws Exception;
}
