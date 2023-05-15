package com.tkmybaitsdemo.demo.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author yb
 * @date 2023/05/151956
 **/
@Data
public class Template {

    @Id
    private String id;

    private String name;

    private String age;

    private String type;

    private String avatar;
}
