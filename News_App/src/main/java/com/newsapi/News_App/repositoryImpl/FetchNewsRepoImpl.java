package com.newsapi.News_App.repositoryImpl;

import com.newsapi.News_App.repository.FetchNewsRepo;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class FetchNewsRepoImpl implements FetchNewsRepo {

    @Override
    public Map getNewsRepo(JSONObject obj) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        //to get data from http://newsapi.org
        Map result = restTemplate.getForObject((String) obj.get("url").toString().trim(), Map.class);

        Map<String, Object> getSource = new HashMap<String, Object>();

        //to store the fetched data in a list
        List articlesList = new ArrayList((Collection) result.get("articles"));

        Map<String, Integer> countOccurance = new HashMap<String, Integer>();

        int count = 1;
        //to add sources in a hashmap along with its occurance count
        for (Object article : articlesList) {
            JSONObject object = new JSONObject((Map) article);
            JSONObject source = new JSONObject((Map) object.get("source"));

            if (countOccurance.isEmpty() || !countOccurance.containsKey(source.get("name"))) {
                countOccurance.put(source.get("name").toString(), count);
            } else {
                int value = (int) countOccurance.get(source.get("name")) + 1;
                countOccurance.put(source.get("name").toString(), value);
            }
        }

        //to get highest count value along with its key from hashmap
        Map.Entry<String, Integer> maxEntry = getEntry(countOccurance);
        List storeNews = new ArrayList();

        //to get news article which has highest occurance count
        for (Object article : articlesList) {
            JSONObject object = new JSONObject((Map) article);
            JSONObject source = new JSONObject((Map) object.get("source"));

            JSONObject newsArticle = new JSONObject();
            if (source.get("name").toString().equalsIgnoreCase(maxEntry.getKey().toString())) {
                newsArticle.put("title", object.get("title"));
                newsArticle.put("description", object.get("description"));
                newsArticle.put("content", object.get("content"));
            }
            if (!newsArticle.isEmpty()) {
                storeNews.add(newsArticle);
            }
        }

        //to add required data in hashmap
        getSource.put("highestOccuranceName", maxEntry.getKey());
        getSource.put("highestOccuranceCount", maxEntry.getValue());
        getSource.put("listOfOccurance", countOccurance);
        getSource.put("news", storeNews);
        return getSource;
    }

    //to get highest count value along with its key from hashmap
    private Map.Entry<String, Integer> getEntry(Map<String, Integer> countOccurance) {
        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : countOccurance.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        return maxEntry;
    }
}
