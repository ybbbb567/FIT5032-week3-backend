package com.tkmybaitsdemo.demo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yb
 * @date 2023/03/130101
 **/
@Data
public class ReportHotline implements Serializable {

    @Id
    @ApiModelProperty(name = "id",notes = "key")
    private String id;

    @NotNull(message = "report cannot be null")
    @ApiModelProperty(name = "reportType",notes = "reportType")
    private String reportType;

    @ApiModelProperty(name = "organization",notes = "organization")
    private String organization;

    @ApiModelProperty(name = "organizationType",notes = "organizationType")
    private String organizationType;

    @ApiModelProperty(name = "scamType",notes = "type of scam")
    private String scamType;

    @ApiModelProperty(name = "detail",notes = "detail of report")
    private String detail;

}
