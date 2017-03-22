package org.fontys.course.registration.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Teacher")
public class Teacher extends Person {

    //TODO is it really needed as field (link to portal).
    @Column
    private String link;

//    private List<Course> MyCourse;
}
