package org.fontys.course.registration.service;

import java.util.List;

import org.fontys.course.registration.model.Admin;
import org.fontys.course.registration.model.Course;
import org.fontys.course.registration.model.Student;
import org.fontys.course.registration.repository.AdminRepository;
import org.fontys.course.registration.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	@Autowired
    private UtilService utilService;
	
	@Autowired
    private AdminRepository adminRepository;
	
	public List<Admin> GetAllAdmins() {
        return this.adminRepository.findAll();
    }
	
	public Admin GetAdmin(Integer pcn) {
        return this.adminRepository.findOne(pcn);
    }
}
