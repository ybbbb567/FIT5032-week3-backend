package com.tkmybaitsdemo.demo.service;

import com.tkmybaitsdemo.demo.entity.Template;
import com.tkmybaitsdemo.demo.util.BaseService;

import java.util.List;

public interface SimulateService extends BaseService<Template> {
    List<Template> getTemplate();
}
