package com.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value={"/student"})
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping(value = "/getStudents")
    public ResponseEntity<Object> getStudents() {
        List<Student> students = studentService.getStudents();
        if(!CollectionUtils.isEmpty(students)) {
        	return ResponseEntity.ok(students);
        }
        return ResponseEntity.ok("No Students found");
    }

    @PostMapping(value="/createStudent")
    public ResponseEntity<String> createUser(@RequestBody Student student){
        System.out.println("Creating Student "+student.toString());
        String status = studentService.createStudent(student);
        System.out.println(status);
        return ResponseEntity.ok(status);
    }
    
    @PutMapping(value="/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestParam int studentId, @RequestParam int marks1,
    		@RequestParam int marks2, @RequestParam int marks3)
    {
       
        return ResponseEntity.ok(studentService.updateStudent(studentId, marks1, marks2, marks3));
    }

}
