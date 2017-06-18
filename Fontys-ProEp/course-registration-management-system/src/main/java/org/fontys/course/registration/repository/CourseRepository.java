package org.fontys.course.registration.repository;

import org.fontys.course.registration.model.Course;
import org.fontys.course.registration.model.enums.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    //TODO be consistent use JPA repository has more priority
    //Replace your code and Query with :  Course findByCode(String courseCode);
    @Query(value = "SELECT * FROM Course WHERE code = ?1", nativeQuery = true)
    Course findCourse(String courseCode);

    List<Course> findByTeachersPcn(Integer pcn);

    @Query(value = "SELECT * FROM course c WHERE c.code IN" +
            "                             (SELECT cs.course_code" +
            "                              FROM course_state cs" +
            "                              WHERE cs.course_type = 'ELECTIVE' AND cs.major = :major AND cs.course_code NOT IN" +
            "                                                                                              (SELECT course_code" +
            "                                                                                               FROM registration r" +
            "                                                                                               WHERE r.student_pcn = :pcn AND r.course_code = cs.course_code))",
            nativeQuery = true)
    List<Course> findAllNotAppliedElectiveCoursesForStudent(@Param("major") String major, @Param("pcn") int pcn);
}

