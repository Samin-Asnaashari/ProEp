package org.fontys.course.registration.model;

import javax.persistence.Entity;

@Entity
public class Admin extends Person{

    public Admin() {
    }

    public Admin(Integer pcn, String password, String email, String firstName, String lastName) {
        super(pcn, password, email, firstName, lastName);
    }
}
