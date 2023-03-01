package com.student;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl implements StudentDao{
    @Autowired
    private SessionFactory sessionFactory;

	@Override
	public String addStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
        session.save(student);
        return "Student created successfully";
	}

	@Override
	public String updateStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		//Student st =(Student)session.get(Student.class, student.getId());
        session.update(student);
        return "Student updated successfully";
	}

	@Override
	public List<Student> getStudents() {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<Student> list= session.createCriteria(Student.class).list();
        return list;
    }

	@Override
	public Student getStudent(int studentId) {
		Session session = sessionFactory.getCurrentSession();
		Student st =(Student)session.get(Student.class, studentId);
		return st;
	}
   

}
