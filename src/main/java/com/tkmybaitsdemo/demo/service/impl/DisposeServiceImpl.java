package com.tkmybaitsdemo.demo.service.impl;

import com.tkmybaitsdemo.demo.entity.Dispose;
import com.tkmybaitsdemo.demo.mapper.DisposeMapper;
import com.tkmybaitsdemo.demo.service.DisposeService;
import com.tkmybaitsdemo.demo.util.AbstractService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author yb
 * @date 2023/03/131359
 **/
@Service
@AllArgsConstructor
public class DisposeServiceImpl extends AbstractService<Dispose> implements DisposeService {

    private final DisposeMapper disposeMapper;
    @Override
    public String getDispWay(String cateName) {
        Example example = new Example(Dispose.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",cateName);
        Dispose resultDispose = disposeMapper.selectOneByExample(example);
        if(resultDispose == null){
            return null;
        }
        return resultDispose.getDisposeWay();
    }
}
