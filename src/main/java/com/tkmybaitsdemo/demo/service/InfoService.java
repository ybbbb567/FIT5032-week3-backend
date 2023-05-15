package com.tkmybaitsdemo.demo.service;

import com.tkmybaitsdemo.demo.entity.Course;
import com.tkmybaitsdemo.demo.entity.FraudData;
import com.tkmybaitsdemo.demo.entity.ReportHotline;
import com.tkmybaitsdemo.demo.util.BaseService;

import java.util.List;

public interface InfoService extends BaseService<FraudData> {

    List<Course> getCourse();

    List<FraudData> getFraudData();

    List<ReportHotline> getReportHotline();
}
