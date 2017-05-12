package org.fontys.course.registration.service;

import org.fontys.course.registration.model.Student;
import org.fontys.course.registration.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

	public List<Student> GetAllStudents() {
		return this.studentRepository.findAll();
	}

	public Student GetStudent(Integer pcn) {
		return this.studentRepository.findOne(pcn);
	}

	public void AddStudent(Student student) {
		this.studentRepository.save(student);
	}

	public void UpdateStudent(Student student) {
		this.studentRepository.save(student);
	}

	public void DeleteStudent(Integer pcn) {
		this.studentRepository.delete(pcn);
	}

	public void GetStudentOfCourse(){
		
	}
}
