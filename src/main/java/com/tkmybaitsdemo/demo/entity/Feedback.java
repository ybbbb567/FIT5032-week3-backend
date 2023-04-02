package com.tkmybaitsdemo.demo.entity;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author yb
 * @date 2023/03/300026
 **/
@Data
public class Feedback {
    @Id
    @ApiModelProperty(name = "id",notes = "key")
    private Long id;

    @NotNull(message = "name cannot be null")
    @ApiModelProperty(name = "name",notes = "name")
    private String name;

    @NotNull(message = "email cannot be null")
    @ApiModelProperty(name = "email",notes = "email")
    private String email;

    @NotNull(message = "rate cannot be null")
    @ApiModelProperty(name = "rate",notes = "rate")
    private Integer rate;

    @NotNull(message = "name cannot be null")
    @ApiModelProperty(name = "content",notes = "content of article")
    private String content;

    @NotNull(message = "createdTime cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_time")
    @ApiModelProperty(name = "created_time",notes = "created time")
    private Date createdTime;

}
