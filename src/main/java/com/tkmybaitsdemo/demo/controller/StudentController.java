package com.tkmybaitsdemo.demo.controller;

import com.tkmybaitsdemo.demo.entity.Student;
import com.tkmybaitsdemo.demo.service.StudentContentService;
import com.tkmybaitsdemo.demo.service.StudentService;
import com.tkmybaitsdemo.demo.util.PageRequest;
import com.tkmybaitsdemo.demo.util.PageResult;
import com.tkmybaitsdemo.demo.util.PageUtils;
import com.tkmybaitsdemo.demo.util.ResultBody;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/student")
@RestController
@AllArgsConstructor
@Api(value = "StudentController测试接口", tags = "student")
public class StudentController {

    private final StudentService studentService;
    private final StudentContentService studentContentService;

    @ApiOperation(value = "新增学生数据", notes = "新增学生数据")
    @RequestMapping(method = RequestMethod.POST, value = "/insert")
    public ResultBody insertStudent(@Validated @RequestBody Student student) {
        studentService.insertStudent(student);
        return ResultBody.success();
    }

    @ApiOperation(value = "@RequestBody分页查询学生数据", notes = "分页查询学生数据")
    @RequestMapping(method = RequestMethod.POST, value = "/queryListByRequestBody")
    public PageResult queryStudentByRequestBody(@RequestBody PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, studentService.pageQuery(pageRequest));
    }

    @ApiOperation(value = "分页查询学生数据", notes = "分页查询学生数据")
    @RequestMapping(method = RequestMethod.POST, value = "/queryListByRequestParam")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页条数"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数")
    })
    public PageResult queryStudentByRequestParam(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, studentService.pageQuery(pageRequest));
    }

    @ApiOperation(value = "修改学生数据", notes = "修改学生数据")
    @PutMapping("/update")
    public ResultBody updateUser(@RequestBody Student student) {
        studentService.updateStudent(student);
        return ResultBody.success();
    }

    @ApiOperation(value = "根据学生id查询学生信息", notes = "根据学生id查询学生信息")
    @GetMapping("/user/{id}")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    public ResultBody queryUserById(@PathVariable(name = "id") Integer id) {
        Student student = studentService.findById(id);
        student.setStudentContents(studentContentService.queryByStudentId(id));
        return ResultBody.success(student);
    }

    @ApiOperation(value = "删除学生数据", notes = "删除学生数据")
    @DeleteMapping("/user/{id}")
    public ResultBody deleteUser(@PathVariable(name = "id") Integer id) {
        studentService.deleteById(id);
        studentContentService.deleteByStudentId(id);
        return ResultBody.success();
    }


}
