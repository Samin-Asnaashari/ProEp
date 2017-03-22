package org.fontys.course.registration.model;

import javax.persistence.*;
import java.util.Date;

@Entity
//@IdClass(RegistrationId.class)
public class Registration {

    @EmbeddedId
    private RegistrationId id;

    @Column
    private Date date;

    public Registration() {
    }

    public RegistrationId getId() {
        return id;
    }

    public void setId(RegistrationId id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
