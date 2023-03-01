package com.student;

import java.util.List;

public interface StudentDao {
    public String addStudent(Student student);
    public String updateStudent(Student student);
	List<Student> getStudents();
	public Student getStudent(int studentId);
}