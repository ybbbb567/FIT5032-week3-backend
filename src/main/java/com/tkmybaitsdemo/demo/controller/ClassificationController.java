package com.tkmybaitsdemo.demo.controller;

import com.tkmybaitsdemo.demo.entity.Website;
import com.tkmybaitsdemo.demo.service.ClassificationService;
import com.tkmybaitsdemo.demo.util.ResultBody;
import com.tkmybaitsdemo.demo.vo.SMSClasResultVO;
import com.tkmybaitsdemo.demo.vo.WebsiteVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

import java.net.URISyntaxException;


/**
 * @author yb
 * @date 2023/04/020200
 **/
@RequestMapping("/classification")
@RestController
@AllArgsConstructor
@CrossOrigin
@Api(value = "Classification test api", tags = "classification")
public class ClassificationController {

    private ClassificationService classificationService;

    @ApiOperation(value = "predict the link", notes = "predict the link")
    @PostMapping(value = "/predict")
    ResultBody predict(@RequestBody Map<String, String> requestBody) throws URISyntaxException {
        String urlString = requestBody.get("link");
        WebsiteVO result = classificationService.predict(urlString);
        if (result == null) {
            return ResultBody.error("Sorry, some error occured in internal system.");
        }
        return ResultBody.success(result);
    }

    @ApiOperation(value = "predict the link", notes = "predict the link")
    @PostMapping(value = "/message")
    ResultBody smsPredict(@RequestBody Map<String, String> requestBody) throws URISyntaxException {
        String message = requestBody.get("message");
        SMSClasResultVO result = classificationService.predictSMS(message);
        if (result == null) {
            return ResultBody.error("Sorry, some error occured in internal system.");
        }
        return ResultBody.success(result);
    }


    @ApiOperation(value = "predict the link", notes = "predict the link")
    @PostMapping(value = "/vote")
    ResultBody vote(@RequestBody Website website) {
        if (!classificationService.vote(website)) {
            return ResultBody.error("Sorry, some error occured in internal system.");
        }
        return ResultBody.success();
    }
}
