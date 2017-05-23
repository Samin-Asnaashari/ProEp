package org.fontys.course.registration.service;

import org.fontys.course.registration.model.Student;
import org.fontys.course.registration.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public void AddStudent(Student student) {
        this.studentRepository.save(student);
    }

    public Student GetStudent(Integer pcn) throws Exception {
        Student student = this.studentRepository.findOne(pcn);
        if (student == null) {
            throw new Exception("Student with pcn=" + pcn + " does not exist.");
        } else {
            return student;
        }
    }

    public Student GetStudentUnsafe(Integer pcn) {
        return this.studentRepository.findOne(pcn);
    }

    public List<Student> GetAllStudents() {
        return this.studentRepository.findAll();
    }

    @Transactional
    public void UpdateStudent(Student student) {
        this.studentRepository.save(student);
    }

    @Transactional
    public void DeleteStudent(Integer pcn) {
        this.studentRepository.delete(pcn);
    }
}
