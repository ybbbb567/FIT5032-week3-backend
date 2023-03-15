package com.tkmybaitsdemo.demo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author yb
 * @date 2023/03/150204
 **/
@Data
public class GarbageVO {
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

    @ApiModelProperty(name = "disposeWay",notes = "dispose way")
    private String disposeWay;
}
