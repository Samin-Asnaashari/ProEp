package org.fontys.course.registration.model;

import org.fontys.course.registration.model.enums.RegistrationStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
//@IdClass(RegistrationId.class)
public class Registration {

    @EmbeddedId
    private RegistrationId id;

    @Column
    private Date date;

    @Column
    private RegistrationStatus registrationStatus;

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

    public RegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}
