package org.fontys.course.registration.service;

import org.fontys.course.registration.model.Teacher;
import org.fontys.course.registration.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

	public List<Teacher> GetAllTeachers() {
		return this.teacherRepository.findAll();
	}

	public Teacher GetTeacher(Integer pcn) {
		return this.teacherRepository.findOne(pcn);
	}

	public void AddTeacher(Teacher teacher) {
		this.teacherRepository.save(teacher);
	}

	public void UpdateTeacher(Teacher teacher) {
		this.teacherRepository.save(teacher);
	}

	public void DeleteTeacher(Integer pcn) {
		this.teacherRepository.delete(pcn);
	}


}
