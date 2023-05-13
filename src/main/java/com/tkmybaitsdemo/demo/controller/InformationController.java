package com.tkmybaitsdemo.demo.controller;

import com.tkmybaitsdemo.demo.entity.Course;
import com.tkmybaitsdemo.demo.entity.FraudData;
import com.tkmybaitsdemo.demo.service.InfoService;
import com.tkmybaitsdemo.demo.util.ResultBody;
import com.tkmybaitsdemo.demo.vo.MapVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yb
 * @date 2023/05/132128
 **/
@RequestMapping("/info")
@RestController
@AllArgsConstructor
@CrossOrigin
@Api(value = "Information test api", tags = "information")
public class InformationController {

    private final InfoService infoService;

    @ApiOperation(value = "get news information", notes = "get news information")
    @GetMapping(value = "/course")
    ResultBody getCourse() {
        List<Course> courseList = infoService.getCourse();
        if(courseList.isEmpty()){
            return ResultBody.error("No courses exists!");
        }
        return ResultBody.success(courseList);
    }

    @ApiOperation(value = "get news information", notes = "get news information")
    @GetMapping(value = "/fraud")
    ResultBody getFraudData() {
        List<FraudData> courseList = infoService.getFraudData();
        if(courseList.isEmpty()){
            return ResultBody.error("No courses exists!");
        }
        return ResultBody.success(courseList);
    }
}
