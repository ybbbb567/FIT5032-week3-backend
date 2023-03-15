package com.tkmybaitsdemo.demo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id",notes = "主键")
    private Integer id;

    @ApiModelProperty(name = "name",notes = "姓名")
    @NotNull(message = "姓名不能为空，必填字段。")
    private String name;

    @ApiModelProperty(name = "studentContents",notes = "属性表")
    @Transient
    private List<StudentContent> studentContents;
}