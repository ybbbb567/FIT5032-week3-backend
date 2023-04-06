package com.tkmybaitsdemo.demo.vo;

import lombok.Data;

/**
 * @author yb
 * @date 2023/04/070023
 **/
@Data
public class WebsiteVO {
    private Long id;

    private String link;

    private Integer likeNum;

    private Integer dislikeNum;

    private ClassificationResultVO predict;
}
