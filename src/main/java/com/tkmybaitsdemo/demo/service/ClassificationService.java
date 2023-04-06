package com.tkmybaitsdemo.demo.service;

import com.tkmybaitsdemo.demo.entity.Website;
import com.tkmybaitsdemo.demo.util.BaseService;
import com.tkmybaitsdemo.demo.vo.ClassificationResultVO;
import com.tkmybaitsdemo.demo.vo.WebsiteVO;

public interface ClassificationService extends BaseService<Website> {
    WebsiteVO predict(String urlString);

    boolean vote(Website website);
}
