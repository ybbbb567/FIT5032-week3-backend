package com.tkmybaitsdemo.demo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author yb
 * @date 2023/04/070025
 **/
@Data
public class Website {

    @Id
    @ApiModelProperty(name = "id",notes = "key")
    private Long id;

    @NotNull(message = "urlString cannot be null")
    @ApiModelProperty(name = "urlString",notes = "urlString")
    private String link;

    @NotNull(message = "like cannot be null")
    @ApiModelProperty(name = "like_num",notes = "likeNum")
    private Integer likeNum;

    @NotNull(message = "dislike cannot be null")
    @ApiModelProperty(name = "dislike_num",notes = "dislike")
    private Integer dislikeNum;
}
