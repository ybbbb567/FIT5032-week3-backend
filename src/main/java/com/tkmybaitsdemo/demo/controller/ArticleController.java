package com.tkmybaitsdemo.demo.controller;

import com.tkmybaitsdemo.demo.entity.Article;
import com.tkmybaitsdemo.demo.service.ArticleService;
import com.tkmybaitsdemo.demo.util.ResultBody;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yb
 * @date 2023/03/131349
 **/
@RequestMapping("/article")
@RestController
@AllArgsConstructor
@CrossOrigin
@Api(value = "ArticleController test api", tags = "article")
public class ArticleController {

    private final ArticleService articleService;

    @ApiOperation(value = "get Article information", notes = "get Article information")
    @GetMapping(value = "/{keyword}")
    ResultBody queryArticle(@RequestBody @PathVariable(name = "keyword") String keyword) {
        List<Article> articleList = articleService.queryArticle(keyword);
        if(articleList.isEmpty()){
            return ResultBody.error("The article you are looking for does not exist!");
        }
        return ResultBody.success(articleList);
    }

    @ApiOperation(value = "get all articles", notes = "get all articles")
    @GetMapping(value = "/all")
    ResultBody queryAllArticle() {
        List<Article> articleList = articleService.queryAllArticle();
        if(articleList.isEmpty()){
            return ResultBody.error("The article you are looking for does not exist!");
        }
        return ResultBody.success(articleList);
    }

}

