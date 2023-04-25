package com.tkmybaitsdemo.demo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author yb
 * @date 2023/04/252235
 **/
@Data
public class MapVO {
    private String stateName;

    private Integer fraudValue;

    private String latitude;

    private String longitude;

    private List<NewsVO> urls;
}
