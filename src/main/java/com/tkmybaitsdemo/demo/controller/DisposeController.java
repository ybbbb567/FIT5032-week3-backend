package com.tkmybaitsdemo.demo.controller;

import com.tkmybaitsdemo.demo.entity.Article;
import com.tkmybaitsdemo.demo.service.DisposeService;
import com.tkmybaitsdemo.demo.util.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yb
 * @date 2023/03/131402
 **/
@RequestMapping("/dispose")
@RestController
@AllArgsConstructor
@CrossOrigin
@Api(value = "DisposeController test api", tags = "dispose")
public class DisposeController {
    private final DisposeService disposeService;

    @ApiOperation(value = "根据学生id查询学生信息", notes = "根据学生id查询学生信息")
    @GetMapping(value = "/{cateName}")
    ResultBody getDispWay(@RequestBody @PathVariable(name = "cateName") String cateName) {
        String disposeWay = disposeService.getDispWay(cateName);
        if(disposeWay == null){
            return ResultBody.error("The category you are looking for does not exist!");
        }
        return ResultBody.success(disposeWay);
    }
}
