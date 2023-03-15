package com.tkmybaitsdemo.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.tkmybaitsdemo.demo.entity.Dispose;
import com.tkmybaitsdemo.demo.entity.Garbage;
import com.tkmybaitsdemo.demo.mapper.DisposeMapper;
import com.tkmybaitsdemo.demo.mapper.GarbageMapper;
import com.tkmybaitsdemo.demo.service.GarbageService;
import com.tkmybaitsdemo.demo.util.AbstractService;
import com.tkmybaitsdemo.demo.vo.GarbageVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author yb
 * @date 2023/03/131400
 **/
@Service
@AllArgsConstructor
public class GarbageServiceImpl extends AbstractService<Garbage> implements GarbageService {

    private final GarbageMapper garbageMapper;

    private final DisposeMapper disposeMapper;

    @Override
    public GarbageVO searchGarbage(String garName) {
        Example example = new Example(Garbage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",garName);
        Garbage garbage = garbageMapper.selectOneByExample(example);
        GarbageVO garbageVO = new GarbageVO();
        if(garbage.getName() == null){
            return garbageVO;
        }
        Example example1 = new Example(Dispose.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("name",garbage.getCategory());
        Dispose dispose = disposeMapper.selectOneByExample(example1);
        if(dispose == null){
            garbageVO.setDisposeWay("no way");
        }else {
            garbageVO.setDisposeWay(dispose.getDisposeWay());
        }
        garbageVO.setId(garbage.getId());
        garbageVO.setName(garbage.getName());
        garbageVO.setCategory(garbage.getCategory());
        garbageVO.setDegradation(garbage.getDegradation());
        return garbageVO;
    }

    @Override
    public List<Garbage> getCategoryInclude(String garCategory) {
        Example example = new Example(Garbage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("category",garCategory);
        return garbageMapper.selectByExample(example);
    }
}
