package com.tkmybaitsdemo.demo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yb
 * @date 2023/03/130108
 **/
@Data
public class Garbage implements Serializable {

    @Id
    @ApiModelProperty(name = "id",notes = "key")
    private String id;

    @NotNull(message = "Name cannot be null")
    @ApiModelProperty(name = "name",notes = "name")
    private String name;

    @ApiModelProperty(name = "category",notes = "category")
    private String category;

    @ApiModelProperty(name = "degradation",notes = "degradation")
    private Integer degradation;
}
