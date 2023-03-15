package com.tkmybaitsdemo.demo.controller;

import com.tkmybaitsdemo.demo.entity.Article;
import com.tkmybaitsdemo.demo.entity.Student;
import com.tkmybaitsdemo.demo.service.ArticleService;
import com.tkmybaitsdemo.demo.util.PageRequest;
import com.tkmybaitsdemo.demo.util.PageResult;
import com.tkmybaitsdemo.demo.util.PageUtils;
import com.tkmybaitsdemo.demo.util.ResultBody;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
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

    @ApiOperation(value = "根据学生id查询学生信息", notes = "根据学生id查询学生信息")
    @GetMapping(value = "/{keyword}")
    ResultBody queryArticle(@RequestBody @PathVariable(name = "garName") String keyword) {
        List<Article> articleList = articleService.queryArticle(keyword);
        if(articleList.isEmpty()){
            return ResultBody.error("The article you are looking for does not exist!");
        }
        return ResultBody.success(articleList);
    }

    @ApiOperation(value = "根据学生id查询学生信息", notes = "根据学生id查询学生信息")
    @GetMapping(value = "/all")
    ResultBody queryAllArticle() {
        List<Article> articleList = articleService.queryAllArticle();
        if(articleList.isEmpty()){
            return ResultBody.error("The article you are looking for does not exist!");
        }
        return ResultBody.success(articleList);
    }

}

