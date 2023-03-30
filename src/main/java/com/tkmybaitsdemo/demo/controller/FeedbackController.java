package com.tkmybaitsdemo.demo.controller;

import com.tkmybaitsdemo.demo.entity.Feedback;
import com.tkmybaitsdemo.demo.service.FeedbackService;
import com.tkmybaitsdemo.demo.util.ResultBody;
import com.tkmybaitsdemo.demo.vo.GarbageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author yb
 * @date 2023/03/300114
 **/
@RequestMapping("/feedback")
@RestController
@AllArgsConstructor
@CrossOrigin
@Api(value = "FeedbackController test api", tags = "feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @ApiOperation(value = "add feedback information", notes = "add feedback information")
    @PostMapping(value = "/")
    ResultBody searchGarbage(@RequestBody Feedback feedback) {
        if(!feedbackService.addFeedback(feedback)){
            return ResultBody.error("Sorry, add feedback failed!");
        }
        return ResultBody.success();
    }
}
