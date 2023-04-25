package com.tkmybaitsdemo.demo.service.impl;

import com.tkmybaitsdemo.demo.entity.News;
import com.tkmybaitsdemo.demo.entity.State;
import com.tkmybaitsdemo.demo.mapper.MapMapper;
import com.tkmybaitsdemo.demo.mapper.NewsMapper;
import com.tkmybaitsdemo.demo.service.MapService;
import com.tkmybaitsdemo.demo.util.AbstractService;
import com.tkmybaitsdemo.demo.vo.MapVO;
import com.tkmybaitsdemo.demo.vo.NewsVO;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author yb
 * @date 2023/04/252246
 **/
@Service
@AllArgsConstructor
public class MapServiceImpl extends AbstractService<State> implements MapService {

    @Resource
    private final MapMapper mapMapper;

    @Resource
    private final NewsMapper newsMapper;

    @Override
    public List<MapVO> getNews() {
        List<State> states = mapMapper.selectAll();
        List<News> newsList = newsMapper.selectAll();
        List<MapVO> mapVOList = new ArrayList<>(); // 存储复制后的 MapVO 列表

        for (State state : states) {
            MapVO mapVO = new MapVO(); // 创建新的 MapVO 对象

            try {
                // 使用 BeanUtils 将 State 对象的属性复制到 MapVO 对象
                BeanUtils.copyProperties(state, mapVO);

                // 复制 NewsVO 列表
                List<NewsVO> newsVOList = new ArrayList<>();
                for (News news : newsList) {
                    if (Objects.equals(news.getStateCode(), state.getStateCode())) {
                        NewsVO newsVO = new NewsVO();
                        BeanUtils.copyProperties(news, newsVO);
                        newsVOList.add(newsVO);
                    }
                }
                mapVO.setUrls(newsVOList);

                // 将 MapVO 对象添加到 MapVO 列表
                mapVOList.add(mapVO);
            } catch (Exception e) {
                // 处理异常
                e.printStackTrace();
            }
        }
        return mapVOList;
    }
}
