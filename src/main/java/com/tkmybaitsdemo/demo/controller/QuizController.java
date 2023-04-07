package com.tkmybaitsdemo.demo.controller;

import com.tkmybaitsdemo.demo.entity.Feedback;
import com.tkmybaitsdemo.demo.entity.Quiz;
import com.tkmybaitsdemo.demo.service.QuizService;
import com.tkmybaitsdemo.demo.util.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yb
 * @date 2023/04/080023
 **/
@RequestMapping("/quiz")
@RestController
@AllArgsConstructor
@CrossOrigin
@Api(value = "Quiz test api", tags = "quiz")
public class QuizController {

    private final QuizService quizService;

    @ApiOperation(value = "get quiz information", notes = "get quiz information")
    @GetMapping(value = "/all")
    ResultBody getQuiz() {
        List<Quiz> quizList = quizService.getAllQuiz();
        if(quizList.isEmpty()){
            return ResultBody.error("No quiz exists!");
        }
        return ResultBody.success(quizList);
    }
}
