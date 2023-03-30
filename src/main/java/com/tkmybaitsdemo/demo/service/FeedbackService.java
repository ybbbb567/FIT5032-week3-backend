package com.tkmybaitsdemo.demo.service;

import com.tkmybaitsdemo.demo.entity.Feedback;
import com.tkmybaitsdemo.demo.util.BaseService;

public interface FeedbackService extends BaseService<Feedback> {
    boolean addFeedback(Feedback feedback);
}
