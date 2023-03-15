package com.tkmybaitsdemo.demo.controller;

import com.tkmybaitsdemo.demo.entity.Garbage;
import com.tkmybaitsdemo.demo.service.GarbageService;
import com.tkmybaitsdemo.demo.util.ResultBody;
import com.tkmybaitsdemo.demo.vo.GarbageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yb
 * @date 2023/03/131404
 **/
@RequestMapping("/garbage")
@RestController
@AllArgsConstructor
@CrossOrigin
@Api(value = "GarbageController test api", tags = "garbage")
public class GarbageController {
    private final GarbageService garbageService;

    @ApiOperation(value = "根据学生id查询学生信息", notes = "根据学生id查询学生信息")
    @GetMapping(value = "/{garName}")
    ResultBody searchGarbage(@RequestBody @PathVariable(name = "garName") String garName) {
        GarbageVO garbageVO = garbageService.searchGarbage(garName);
        if(garbageVO.getId() == null){
            return ResultBody.error("Sorry, we don't have any junk with this name included at the moment!");
        }
        return ResultBody.success(garbageVO);
    }

    @ApiOperation(value = "根据学生id查询学生信息", notes = "根据学生id查询学生信息")
    @GetMapping(value = "/category/{garCategory}")
    ResultBody getCategoryInclude(@RequestBody @PathVariable(name = "garCategory")String garCategory) {
        List<Garbage> garbageList = garbageService.getCategoryInclude(garCategory);
        if(garbageList.isEmpty()){
            return ResultBody.error("Category query error!");
        }
        return ResultBody.success(garbageList);
    }
}
