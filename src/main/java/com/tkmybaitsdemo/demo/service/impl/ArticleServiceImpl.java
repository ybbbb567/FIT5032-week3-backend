package com.tkmybaitsdemo.demo.service.impl;

import com.tkmybaitsdemo.demo.entity.Article;
import com.tkmybaitsdemo.demo.mapper.ArticleMapper;
import com.tkmybaitsdemo.demo.service.ArticleService;
import com.tkmybaitsdemo.demo.util.AbstractService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author yb
 * @date 2023/03/131350
 **/
@Service
@AllArgsConstructor
public class ArticleServiceImpl extends AbstractService<Article> implements ArticleService {

    private final ArticleMapper articleMapper;

    @Override
    public List<Article> queryArticle(String keyword) {
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("title","%" + keyword + "%");
        return articleMapper.selectByExample(example);
    }

    @Override
    public List<Article> queryAllArticle() {
        return articleMapper.selectAll();
    }
}
