package com.student;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;


public interface StudentService {
    public String createStudent(Student student);
    public List<Student> getStudents();
    public String updateStudent(int studentId, int marks1,
    		int marks2, int marks3);
}