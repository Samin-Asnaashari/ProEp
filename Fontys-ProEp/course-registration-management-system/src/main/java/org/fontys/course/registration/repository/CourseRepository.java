package org.fontys.course.registration.repository;

import org.fontys.course.registration.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    //TODO this is example.
    //    List<MenuItem> findByMenuItemIsNullAndTrashIsTrue();
    //TODO please for more Ino check the website: http://docs.spring.io/spring-data/jpa/docs/1.4.1.RELEASE/reference/html/jpa.repositories.html
}


