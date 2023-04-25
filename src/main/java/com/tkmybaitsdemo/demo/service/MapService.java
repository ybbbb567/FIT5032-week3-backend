package com.tkmybaitsdemo.demo.service;

import com.tkmybaitsdemo.demo.entity.State;
import com.tkmybaitsdemo.demo.util.BaseService;
import com.tkmybaitsdemo.demo.vo.MapVO;

import java.util.List;

public interface MapService extends BaseService<State> {
    List<MapVO> getNews();
}
