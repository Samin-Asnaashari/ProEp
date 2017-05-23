package org.fontys.course.registration.model;

import org.fontys.course.registration.model.enums.Permission;

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

    //TODO Think about it this doesn't feel right maybe having Admin is better.
    @Enumerated(EnumType.STRING)
    private Permission permission;

    public Teacher() {
    }

    public Teacher(Integer pcn, String password, String email, String firstName, String lastName, String link, Permission permission) {
        super(pcn, password, email, firstName, lastName);
        this.link = link;
        this.permission = permission;
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

    public List<Course> getMyCourses() {
        return myCourses;
    }

    public void setMyCourses(List<Course> myCourses) {
        this.myCourses = myCourses;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
