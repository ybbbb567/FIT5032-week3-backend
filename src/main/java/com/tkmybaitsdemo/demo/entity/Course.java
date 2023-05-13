package com.tkmybaitsdemo.demo.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author yb
 * @date 2023/05/132153
 **/
@Data
public class Course {
    @Id
    private String link;
}
