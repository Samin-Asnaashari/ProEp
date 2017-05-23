package org.fontys.course.registration.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Teacher extends Person {

    //TODO is it really needed as field (link to portal).
    @Column
    private String link;
    
    @JsonIgnoreProperties("teachers")
    @ManyToMany(mappedBy = "teachers")
    private List<Course> myCourses;

    public Teacher() {
    }

    public Teacher(Integer pcn, String password, String email, String firstName, String lastName, String link) {
        super(pcn, password, email, firstName, lastName);
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Course> getMyCourses() {
        return myCourses;
    }

    public void setMyCourses(List<Course> myCourses) {
        this.myCourses = myCourses;
    }
}
