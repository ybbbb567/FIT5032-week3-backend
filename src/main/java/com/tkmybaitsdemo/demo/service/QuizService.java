package com.tkmybaitsdemo.demo.service;

import com.tkmybaitsdemo.demo.entity.Garbage;
import com.tkmybaitsdemo.demo.entity.Quiz;
import com.tkmybaitsdemo.demo.util.BaseService;

import java.util.List;

public interface QuizService extends BaseService<Quiz> {
    List<Quiz> getAllQuiz();
}
