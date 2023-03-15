package com.tkmybaitsdemo.demo.service;

import com.tkmybaitsdemo.demo.entity.Dispose;
import com.tkmybaitsdemo.demo.util.BaseService;

public interface DisposeService extends BaseService<Dispose> {

    String getDispWay(String cateName);
}
