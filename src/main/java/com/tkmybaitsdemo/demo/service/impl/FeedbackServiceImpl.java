package com.tkmybaitsdemo.demo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tkmybaitsdemo.demo.entity.Feedback;
import com.tkmybaitsdemo.demo.mapper.FeedbackMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yb
 * @date 2023/03/300037
 **/
@Service
@AllArgsConstructor
public class FeedbackServiceImpl {
    private final FeedbackMapper feedbackMapper;

//    @Override
    public boolean addFeedback(Feedback feedback){
        if(ObjectUtil.isAllNotEmpty(feedback)){
            return feedbackMapper.insert(feedback) >0;
        }
        return false;
    }
}
