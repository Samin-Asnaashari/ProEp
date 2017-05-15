package org.fontys.course.registration.model;

import org.fontys.course.registration.model.enums.RegistrationStatus;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
//@IdClass(RegistrationId.class)
public class Registration {

    @EmbeddedId
    private RegistrationId id;

    @Column
    private Date date;

    @Enumerated(EnumType.STRING)
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
