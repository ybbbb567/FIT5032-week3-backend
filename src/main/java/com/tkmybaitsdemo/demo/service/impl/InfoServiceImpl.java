package com.tkmybaitsdemo.demo.service.impl;

import com.tkmybaitsdemo.demo.entity.Course;
import com.tkmybaitsdemo.demo.entity.FraudData;
import com.tkmybaitsdemo.demo.entity.ReportHotline;
import com.tkmybaitsdemo.demo.mapper.CourseMapper;
import com.tkmybaitsdemo.demo.mapper.FraudDataMapper;
import com.tkmybaitsdemo.demo.mapper.ReportHotlineMapper;
import com.tkmybaitsdemo.demo.service.InfoService;
import com.tkmybaitsdemo.demo.util.AbstractService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yb
 * @date 2023/05/132143
 **/
@Service
@AllArgsConstructor
public class InfoServiceImpl extends AbstractService<FraudData> implements InfoService {

    @Resource
    private final CourseMapper courseMapper;

    @Resource
    private final FraudDataMapper fraudDataMapper;

    @Resource
    private final ReportHotlineMapper reportHotlineMapper;

    @Override
    public List<Course> getCourse() {
        return courseMapper.selectAll();
    }

    @Override
    public List<FraudData> getFraudData() {
        return fraudDataMapper.selectAll();
    }

    @Override
    public List<ReportHotline> getReportHotline() {return reportHotlineMapper.selectAll(); }

}
