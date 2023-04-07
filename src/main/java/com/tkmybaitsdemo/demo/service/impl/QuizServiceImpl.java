package com.tkmybaitsdemo.demo.service.impl;

import com.tkmybaitsdemo.demo.entity.Feedback;
import com.tkmybaitsdemo.demo.entity.Quiz;
import com.tkmybaitsdemo.demo.mapper.QuizMapper;
import com.tkmybaitsdemo.demo.service.FeedbackService;
import com.tkmybaitsdemo.demo.service.QuizService;
import com.tkmybaitsdemo.demo.util.AbstractService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yb
 * @date 2023/04/080026
 **/
@Service
@AllArgsConstructor
public class QuizServiceImpl extends AbstractService<Quiz> implements QuizService {

    @Resource
    private final QuizMapper quizMapper;

    @Override
    public List<Quiz> getAllQuiz() {
        return quizMapper.selectAll();
    }
}
