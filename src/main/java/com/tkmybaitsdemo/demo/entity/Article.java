package com.tkmybaitsdemo.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author yb
 * @date 2023/03/130101
 **/
@Data
public class Article implements Serializable {

    @Id
    @ApiModelProperty(name = "id",notes = "key")
    private String id;

    @NotNull(message = "Title cannot be null")
    @ApiModelProperty(name = "title",notes = "title")
    private String title;

    @ApiModelProperty(name = "author",notes = "author")
    private String author;

    @ApiModelProperty(name = "createdTime",notes = "created time")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;

    @ApiModelProperty(name = "content",notes = "content of article")
    private String content;


    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
}
