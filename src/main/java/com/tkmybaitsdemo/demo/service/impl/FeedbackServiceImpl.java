package com.tkmybaitsdemo.demo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tkmybaitsdemo.demo.entity.Article;
import com.tkmybaitsdemo.demo.entity.Feedback;
import com.tkmybaitsdemo.demo.entity.Garbage;
import com.tkmybaitsdemo.demo.mapper.FeedbackMapper;
import com.tkmybaitsdemo.demo.service.FeedbackService;
import com.tkmybaitsdemo.demo.util.AbstractService;
import com.tkmybaitsdemo.demo.util.SnowflakeIdGenerator;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author yb
 * @date 2023/03/300037
 **/
@Service
@AllArgsConstructor
public class FeedbackServiceImpl extends AbstractService<Feedback> implements FeedbackService {
    @Resource
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

    @Override
    public List<Feedback> getFeedback() {
        Example example = new Example(Feedback.class);
        example.setOrderByClause("created_time DESC");
        return feedbackMapper.selectByExample(example);
    }

}
