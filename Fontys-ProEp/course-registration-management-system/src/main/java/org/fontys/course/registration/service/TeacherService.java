package org.fontys.course.registration.service;

import org.fontys.course.registration.model.Teacher;
import org.fontys.course.registration.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Transactional
    public void AddTeacher(Teacher teacher) {
        this.teacherRepository.save(teacher);
    }

    public Teacher GetTeacher(Integer pcn) throws Exception {
        Teacher teacher = this.teacherRepository.findOne(pcn);
        if (teacher == null) {
            throw new Exception("Teacher with pcn=" + pcn + " does not exist.");
        } else {
            return teacher;
        }
    }

    public Teacher GetTeacherUnsafe(Integer pcn) {
        return this.teacherRepository.findOne(pcn);
    }

    public List<Teacher> GetAllTeachers() {
        return this.teacherRepository.findAll();
    }

    @Transactional
    public void UpdateTeacher(Teacher teacher) {
        this.teacherRepository.save(teacher);
    }

    @Transactional
    public void DeleteTeacher(Integer pcn) {
        this.teacherRepository.delete(pcn);
    }
}
