package com.tkmybaitsdemo.demo.service;

import com.tkmybaitsdemo.demo.entity.Article;
import com.tkmybaitsdemo.demo.util.BaseService;

import java.util.List;

public interface ArticleService extends BaseService<Article> {
    List<Article> queryArticle(String keyword);

    List<Article> queryAllArticle();
}
