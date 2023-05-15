package com.tkmybaitsdemo.demo.service.impl;

import com.tkmybaitsdemo.demo.entity.Template;
import com.tkmybaitsdemo.demo.mapper.TemplateMapper;
import com.tkmybaitsdemo.demo.service.SimulateService;
import com.tkmybaitsdemo.demo.util.AbstractService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yb
 * @date 2023/05/152018
 **/
@Service
@AllArgsConstructor
public class SimulateServiceImpl extends AbstractService<Template> implements SimulateService {


    @Resource
    private final TemplateMapper templateMapper;

    @Override
    public List<Template> getTemplate() {
        return templateMapper.selectAll();
    }
}
