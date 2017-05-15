package org.fontys.course.registration.model;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue(value = "Teacher")
public class Teacher extends Person {

    //TODO is it really needed as field (link to portal).
    @Column
    private String link;

    @ManyToMany(mappedBy = "teachers")
    private List<Course> myCourses;

    public Teacher() {
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Course> getMyCourse() {
        return myCourses;
    }

    public void setMyCourse(List<Course> myCourses) {
        this.myCourses = myCourses;
    }
}
