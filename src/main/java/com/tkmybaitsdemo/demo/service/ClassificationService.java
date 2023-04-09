package com.tkmybaitsdemo.demo.service;

import com.tkmybaitsdemo.demo.entity.Website;
import com.tkmybaitsdemo.demo.util.BaseService;
import com.tkmybaitsdemo.demo.vo.WebsiteVO;

import java.net.URISyntaxException;

public interface ClassificationService extends BaseService<Website> {
    WebsiteVO predict(String urlString) throws URISyntaxException;

    boolean vote(Website website);
}
