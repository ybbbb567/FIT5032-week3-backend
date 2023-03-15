package com.tkmybaitsdemo.demo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yb
 * @date 2023/03/130107
 **/
@Data
public class Dispose implements Serializable {

    @Id
    @ApiModelProperty(name = "id",notes = "key")
    private String id;

    @NotNull(message = "name cannot be null")
    @ApiModelProperty(name = "name",notes = "name")
    private String name;

    @ApiModelProperty(name = "disposeWay",notes = "dispose way")
    private String disposeWay;

}
