package com.tkmybaitsdemo.demo.service;

import com.tkmybaitsdemo.demo.entity.StudentContent;
import com.tkmybaitsdemo.demo.util.BaseService;

import java.util.List;

public interface StudentContentService extends BaseService<StudentContent> {

   List<StudentContent> queryByStudentId(Integer id);
   boolean deleteByStudentId(Integer id);
}
