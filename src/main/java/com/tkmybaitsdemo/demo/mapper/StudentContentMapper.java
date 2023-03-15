package com.tkmybaitsdemo.demo.mapper;

import com.tkmybaitsdemo.demo.entity.StudentContent;
import com.tkmybaitsdemo.demo.util.MyMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentContentMapper extends MyMapper<StudentContent> {

      String sql = "select id,age,nike,address,student_id as studentId from student_content";

    @Select(sql + " where student_id = #{id}")
    List<StudentContent> queryByStudentId(@Param("id") Integer id);

    @Delete("delete from student_content where student_id = #{id}")
    boolean deleteByStudentId(@Param("id") Integer id);
}