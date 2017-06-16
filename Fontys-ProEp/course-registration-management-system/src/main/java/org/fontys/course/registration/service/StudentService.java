package org.fontys.course.registration.service;

import org.fontys.course.registration.model.Person;
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
    
    @Autowired
    private UtilService utilService;

    @Transactional
    public void AddStudent(Student student) {
    	student.setNotificationBadgeCount(0);
        this.studentRepository.save(student);
    }

    @Transactional
    public void AddStudents(List<Student> students) {
    	for(int i = 0; i < students.size(); i++) {
    		students.get(i).setNotificationBadgeCount(0);
    	}
        this.studentRepository.save(students);
    }

    public Student GetStudent(Integer pcn) throws Exception {
        Student student = this.studentRepository.findOne(pcn);
        if (student == null) {
            throw new Exception("Student with pcn=" + pcn + " does not exist.");
        } else {
            return student;
        }
    }

    @Transactional
    public Student GetStudentUnsafe(Integer pcn) {
        return this.studentRepository.findOne(pcn);
    }

    @Transactional
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

    @Transactional
    public void DeleteStudents(List<Student> students) {
        for (Student student : students) { // TODO double check if student exist
            this.studentRepository.delete(student);
        }
    }

    @Transactional
	public void AddPushNotificationToken(Integer pcn, String pushNotificationToken) {
		this.studentRepository.findOne(pcn).setPushNotificationToken(pushNotificationToken);
	}

    @Transactional
	public void IncreaseNotificationBadgeCount(Person person) {
    	Student student = this.studentRepository.findOne(person.getPcn());
    	student.setNotificationBadgeCount(student.getNotificationBadgeCount() + 1);
	}

    @Transactional
	public void ClearAmountOfBadges(Integer pcn) {
		this.studentRepository.findOne(pcn).setNotificationBadgeCount(0);
	}

    @Transactional
	public Integer GetAmountOfBadges(Integer pcn) {
		return this.studentRepository.findOne(pcn).getNotificationBadgeCount();
	}
}
