package com.tkmybaitsdemo.demo.service;

import com.tkmybaitsdemo.demo.entity.Garbage;
import com.tkmybaitsdemo.demo.util.BaseService;
import com.tkmybaitsdemo.demo.vo.GarbageVO;

import java.util.List;

public interface GarbageService extends BaseService<Garbage> {

    GarbageVO searchGarbage(String garName);

    List<Garbage> getCategoryInclude(String garCategory);
}
