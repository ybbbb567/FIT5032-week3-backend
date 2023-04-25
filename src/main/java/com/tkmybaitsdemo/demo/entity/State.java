package com.tkmybaitsdemo.demo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;

/**
 * @author yb
 * @date 2023/04/252224
 **/
@Data
public class State {
    @Id
    @ApiModelProperty(name = "id",notes = "key")
    private String stateCode;

    @ApiModelProperty(name = "state",notes = "state")
    private String stateName;

    @ApiModelProperty(name = "fraudValue",notes = "fraudValue")
    private Integer fraudValue;

    @ApiModelProperty(name = "latitude",notes = "latitude")
    private String latitude;

    @ApiModelProperty(name = "longitude",notes = "longitude")
    private String longitude;

}
