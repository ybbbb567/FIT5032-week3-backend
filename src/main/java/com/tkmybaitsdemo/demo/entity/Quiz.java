package com.tkmybaitsdemo.demo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author yb
 * @date 2023/04/080018
 **/
@Data
public class Quiz {

    @Id
    @ApiModelProperty(name = "id",notes = "key")
    private String id;

    @NotNull(message = "question cannot be null")
    @ApiModelProperty(name = "question",notes = "question")
    private String question;

    @ApiModelProperty(name = "optionA",notes = "optionA")
    private String optionA;

    @ApiModelProperty(name = "optionB",notes = "optionB")
    private String optionB;

    @ApiModelProperty(name = "optionC",notes = "optionC")
    private String optionC;

    @ApiModelProperty(name = "optionD",notes = "optionD")
    private String optionD;

    @ApiModelProperty(name = "answer",notes = "answer")
    private String answer;

    @ApiModelProperty(name = "explanation",notes = "explanation")
    private String explanation;
}
