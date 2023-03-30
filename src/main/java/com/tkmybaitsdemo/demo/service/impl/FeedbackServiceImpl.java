package com.tkmybaitsdemo.demo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tkmybaitsdemo.demo.entity.Feedback;
import com.tkmybaitsdemo.demo.entity.Garbage;
import com.tkmybaitsdemo.demo.mapper.FeedbackMapper;
import com.tkmybaitsdemo.demo.service.FeedbackService;
import com.tkmybaitsdemo.demo.util.AbstractService;
import com.tkmybaitsdemo.demo.util.SnowflakeIdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * @author yb
 * @date 2023/03/300037
 **/
@Service
@AllArgsConstructor
public class FeedbackServiceImpl extends AbstractService<Feedback> implements FeedbackService {
    private final FeedbackMapper feedbackMapper;

    @Autowired
    private final SnowflakeIdGenerator snowflakeIdGenerator;

    @Override
    public boolean addFeedback(Feedback feedback){
        feedback.setId(snowflakeIdGenerator.nextId());
        if(ObjectUtil.isAllNotEmpty(feedback)){
            return feedbackMapper.insert(feedback) >0;
        }
        return false;
    }
}
