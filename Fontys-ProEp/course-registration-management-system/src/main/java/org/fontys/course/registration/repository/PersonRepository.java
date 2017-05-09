package org.fontys.course.registration.repository;

import org.fontys.course.registration.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    //TODO this is example.
    //    List<MenuItem> findByMenuItemIsNullAndTrashIsTrue();
    //TODO please for more Ino check the website: http://docs.spring.io/spring-data/jpa/docs/1.4.1.RELEASE/reference/html/jpa.repositories.html

   // List<Person> findAllByStudentNumber(Integer StudentNumber);

    @Query("select p from Student")
    List<Person> findAllStudents();

    @Query("select p from Teacher")
    List<Person> findAllTeachers();

    @Query("select s from Student where s.pcn = ?1 ")
    Person findStudentByPcn(Integer pcn);

    @Query("select t from Teacher where t.pcn = ?1 ")
    Person findTeacherByPcn(Integer pcn);
}


