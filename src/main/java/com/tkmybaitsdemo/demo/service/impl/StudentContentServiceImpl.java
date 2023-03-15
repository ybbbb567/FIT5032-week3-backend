package com.tkmybaitsdemo.demo.service.impl;

import com.tkmybaitsdemo.demo.entity.StudentContent;
import com.tkmybaitsdemo.demo.mapper.StudentContentMapper;
import com.tkmybaitsdemo.demo.service.StudentContentService;
import com.tkmybaitsdemo.demo.util.AbstractService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentContentServiceImpl extends AbstractService<StudentContent> implements StudentContentService {

    private final StudentContentMapper studentContentMapper;

    @Override
    public List<StudentContent> queryByStudentId(Integer id) {
        return studentContentMapper.queryByStudentId(id);
    }

    @Override
    public boolean deleteByStudentId(Integer id) {
        return studentContentMapper.deleteByStudentId(id);
    }
}
