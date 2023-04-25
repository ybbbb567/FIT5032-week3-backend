package com.tkmybaitsdemo.demo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;

/**
 * @author yb
 * @date 2023/04/252229
 **/
@Data
public class News {

    @Id
    @ApiModelProperty(name = "id",notes = "key")
    private String stateCode;

    @ApiModelProperty(name = "id",notes = "key")
    private String title;

    @ApiModelProperty(name = "id",notes = "key")
    private String link;
}
