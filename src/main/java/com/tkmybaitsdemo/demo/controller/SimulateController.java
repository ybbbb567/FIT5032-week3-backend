package com.tkmybaitsdemo.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.tkmybaitsdemo.demo.api.OpenAi;
import com.tkmybaitsdemo.demo.entity.Feedback;
import com.tkmybaitsdemo.demo.util.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yb
 * @date 2023/05/110216
 **/
@RequestMapping("/simulate")
@RestController
@AllArgsConstructor
@CrossOrigin
@Api(value = "Simulate test api", tags = "simulate")
public class SimulateController {
    @Autowired
    private OpenAi openAi;

    @ApiOperation(value = "chat with gpt model", notes = "chat with gpt model")
    @PostMapping(value = "/chat")
    ResultBody chat(@RequestBody List<JSONObject> messages) {
        String content = openAi.chat(messages);
        if(content.equals("Error!")){
            return ResultBody.error("Sorry, there is something wrong with our chat model!");
        }
        return ResultBody.success(content);
    }
}
