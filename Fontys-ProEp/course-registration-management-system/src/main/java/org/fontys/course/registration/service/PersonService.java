package org.fontys.course.registration.service;

import org.fontys.course.registration.model.Person;
import org.fontys.course.registration.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

	public List<Person> GetAllStudents() {return this.personRepository.findAllStudents();}

	public List<Person> GetAllTeachers() {return this.personRepository.findAllTeachers();}

	//public Person GetPerson(Integer pcn) {return this.personRepository.findOne(pcn);	}

	public Person GetStudent(Integer pcn) {return this.personRepository.findStudentByPcn(pcn);}

	public Person GetTeacher(Integer pcn) {return this.personRepository.findTeacherByPcn(pcn);}

	public void AddPerson(Person person) {
		this.personRepository.save(person);
	}

	public void UpdatePerson(Person person) {
		this.personRepository.save(person);
	}

	public void DeletePerson(Integer pcn) {
		this.personRepository.delete(pcn);
	}

    //TODO this is example.
//    @Transactional //only when is needed
//    @Override
//    public Block create(Block block) throws Exception {
//        Style style = new Style();
//        style = this.styleService.create(style);
//        block.setStyle(style);
//        return this.blockRepository.save(block);
//    }
}
