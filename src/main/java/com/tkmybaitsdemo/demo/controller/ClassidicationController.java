package com.tkmybaitsdemo.demo.controller;

import com.tkmybaitsdemo.demo.model.ClassificationModel;
import com.tkmybaitsdemo.demo.util.ResultBody;
import com.tkmybaitsdemo.demo.vo.ClassificationResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author yb
 * @date 2023/04/020200
 **/
@RequestMapping("/classification")
@RestController
@AllArgsConstructor
@CrossOrigin
@Api(value = "Classification test api", tags = "classification")
public class ClassidicationController {

    @Autowired
    private ClassificationModel classificationModel;

    @ApiOperation(value = "get all articles", notes = "get all articles")
    @PostMapping(value = "/predict")
    ResultBody predict(@RequestBody String urlString){
        ClassificationResultVO result = classificationModel.predict(urlString);
        if(result == null){
            return ResultBody.error("Sorry, some error occured in internal system.");
        }
        return ResultBody.success(result);
    }
}
