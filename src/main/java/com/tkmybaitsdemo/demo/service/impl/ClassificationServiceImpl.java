package com.tkmybaitsdemo.demo.service.impl;

import com.tkmybaitsdemo.demo.entity.Website;
import com.tkmybaitsdemo.demo.mapper.WebsiteMapper;
import com.tkmybaitsdemo.demo.model.ClassificationModel;
import com.tkmybaitsdemo.demo.service.ClassificationService;
import com.tkmybaitsdemo.demo.util.AbstractService;
import com.tkmybaitsdemo.demo.util.SnowflakeIdGenerator;
import com.tkmybaitsdemo.demo.vo.ClassificationResultVO;
import com.tkmybaitsdemo.demo.vo.WebsiteVO;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @author yb
 * @date 2023/04/070014
 **/
@Service
@AllArgsConstructor
public class ClassificationServiceImpl extends AbstractService<Website> implements ClassificationService {

    @Resource
    private final WebsiteMapper websiteMapper;

    @Autowired
    private final SnowflakeIdGenerator snowflakeIdGenerator;

    @Autowired
    private ClassificationModel classificationModel;

    @Override
    public WebsiteVO predict(String urlString) {
        Example example = new Example(Website.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("link", urlString);
        Website website = websiteMapper.selectOneByExample(example);
        WebsiteVO websiteVO = new WebsiteVO();
        if (website == null) {
            //there is no this link record in database
            websiteVO.setId(snowflakeIdGenerator.nextId());
            websiteVO.setLink(urlString);
            websiteVO.setLikeNum(0);
            websiteVO.setDislikeNum(0);
        } else {
            BeanUtils.copyProperties(website, websiteVO);
        }
        websiteVO.setPredict(classificationModel.predict(urlString));
        return websiteVO;
    }

    @Override
    public boolean vote(Website website) {
        if (websiteMapper.selectByPrimaryKey(website.getId()) == null) {
            return websiteMapper.insert(website) > 0;
        }
        return websiteMapper.updateByPrimaryKey(website) > 0;
    }
}
