package com.tkmybaitsdemo.demo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "student_content")
@Data
public class StudentContent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id",notes = "主键")
    private Integer id;
    @ApiModelProperty(name = "age",notes = "年龄")
    private Integer age;
    @ApiModelProperty(name = "nike",notes = "爱好")
    private String nike;
    @ApiModelProperty(name = "address",notes = "地址")
    private String address;
    @ApiModelProperty(name = "student_id",notes = "学生表id")
    @Column(name = "student_id")
    private Integer studentId;

}