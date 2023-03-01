package com.student;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;


@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao;

	@Override
	public String createStudent(Student student) {
		if(ObjectUtils.isEmpty(student)) {
			return "Student information not found";
		}
		if(student.getFirstName().length() < 3 || student.getLastName().length() < 3) {
			return "Student First name and last name should have atleast 3 characters";
		}
		if(!student.getSection().equalsIgnoreCase("A") && 
				!student.getSection().equalsIgnoreCase("B") &&
				!student.getSection().equalsIgnoreCase("C")) {
			return "Student section should be A or B or C";
		}
		if(!student.getGender().equalsIgnoreCase("M") &&
				!student.getGender().equalsIgnoreCase("F")) {
			return "Student Gender should be M or F";
		}
		if(student.getDob() != null) { 
			Calendar cal = Calendar.getInstance();
			cal.setTime(student.getDob());
			int month = cal.get(Calendar.MONTH);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int year = cal.get(Calendar.YEAR);
			LocalDate dob = LocalDate.of(year,month,day);
			
			LocalDate curDate = LocalDate.now();  
			
			Period period = Period.between(dob, curDate);  
			
			int age = period.getYears();
			
	        if(age <= 15 || age > 20) {
	        	return "Student Age should be greater than 15 year and less than or equal to 20 years";
	        }
		} else {
			return "Student DOB is not valid";
		}
		
		if(student.getMarks1() < 0 || student.getMarks1()> 100) {
			return "Student Marks1 is not valid";
		}
		
		if(student.getMarks2() < 0 || student.getMarks2()> 100) {
			return "Student Marks2 is not valid";
		}
		
		if(student.getMarks3() < 0 || student.getMarks3()> 100) {
			return "Student Marks3 is not valid";
		}
		calStudentMarks(student);
		
		return studentDao.addStudent(student);
	}
	
	public void calStudentMarks(Student student) {		
		int total = student.getMarks1()+ student.getMarks2() + student.getMarks3();
		
		student.setTotal(total);
		if(total > 0) {
			student.setAvg(total/3);
		}
		if(student.getMarks1() >= 35 && student.getMarks2() >= 35 && student.getMarks3() >= 35) {
			student.setResult("Passed");
		} else {
			student.setResult("Failed");
		}
	}

	@Override
	public List<Student> getStudents() {
		return studentDao.getStudents();
	}

	@Override
	public String updateStudent(int studentId, int marks1,
    		int marks2, int marks3) {
		
		Student student = studentDao.getStudent(studentId);
		student.setMarks1(marks1);
		student.setMarks2(marks2);
		student.setMarks3(marks3);
		
		if(student.getMarks1() < 0 || student.getMarks1()> 100) {
			return "Student Marks1 is not valid";
		}
		
		if(student.getMarks2() < 0 || student.getMarks2()> 100) {
			return "Student Marks2 is not valid";
		}
		
		if(student.getMarks3() < 0 || student.getMarks3()> 100) {
			return "Student Marks3 is not valid";
		}
		
		calStudentMarks(student);
		
		return studentDao.updateStudent(student);
	}


   

}