package com.tkmybaitsdemo.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tkmybaitsdemo.demo.entity.Student;
import com.tkmybaitsdemo.demo.entity.StudentContent;
import com.tkmybaitsdemo.demo.exception.BizException;
import com.tkmybaitsdemo.demo.mapper.StudentContentMapper;
import com.tkmybaitsdemo.demo.service.StudentService;
import com.tkmybaitsdemo.demo.util.AbstractService;
import com.tkmybaitsdemo.demo.util.PageRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl extends AbstractService<Student> implements StudentService {

    private final StudentContentMapper contentMapper;
    @Override
    @Transactional(rollbackFor = BizException.class)
    public boolean insertStudent(Student student) {
        List<StudentContent> studentContents = student.getStudentContents();
        this.save(student);
        studentContents.forEach(e->{
            e.setStudentId(student.getId());
            contentMapper.insert(e);
            System.out.println("studentContentId: -> "+e.getId());
        });
        return true;
    }

    @Override
    @Transactional(rollbackFor = BizException.class)
    public boolean updateStudent(Student student) {
        update(student);
        student.getStudentContents().forEach(contentMapper::updateByPrimaryKeySelective);
        return true;
    }

    @Override
    public PageInfo<Student> pageQuery(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Student> all = this.findAll();
        all.forEach(e->
            e.setStudentContents(contentMapper.queryByStudentId(e.getId()))
        );
        return new PageInfo<>(all);
    }
}
