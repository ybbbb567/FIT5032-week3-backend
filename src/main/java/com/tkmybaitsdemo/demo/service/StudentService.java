package com.tkmybaitsdemo.demo.service;

import com.github.pagehelper.PageInfo;
import com.tkmybaitsdemo.demo.entity.Student;
import com.tkmybaitsdemo.demo.util.BaseService;
import com.tkmybaitsdemo.demo.util.PageRequest;

public interface StudentService extends BaseService<Student> {


    boolean insertStudent(Student student);
    boolean updateStudent(Student student);

    PageInfo<Student> pageQuery(PageRequest pageRequest);

}
