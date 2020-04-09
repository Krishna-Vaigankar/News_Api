package com.newsapi.News_App.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class PageController {

    @GetMapping("/")
    public ModelAndView indexPage() {
        return new ModelAndView("news");
    }

    @GetMapping("/**")
    public ModelAndView otherPages() {
        return new ModelAndView("notFound");
    }
}
