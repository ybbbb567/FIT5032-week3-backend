package com.tkmybaitsdemo.demo.controller;

import com.tkmybaitsdemo.demo.service.MapService;
import com.tkmybaitsdemo.demo.util.ResultBody;
import com.tkmybaitsdemo.demo.vo.MapVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yb
 * @date 2023/04/252232
 **/
@RequestMapping("/map")
@RestController
@AllArgsConstructor
@CrossOrigin
@Api(value = "Map test api", tags = "map")
public class MapController {

    private MapService mapService;

    @ApiOperation(value = "get news information", notes = "get news information")
    @GetMapping(value = "/news")
    ResultBody getNews() {
        List<MapVO> stateNews = mapService.getNews();
        if(stateNews.isEmpty()){
            return ResultBody.error("No states and news exists!");
        }
        return ResultBody.success(stateNews);
    }
}
