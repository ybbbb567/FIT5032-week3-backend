package com.tkmybaitsdemo.demo.provider;

public class StudentProvider {

    public String queryStudent(){
        return "select s.*,sc.* from student s inner join student_content sc on s.id = sc.student_id";
    }
}
