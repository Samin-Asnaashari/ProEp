package org.fontys.course.registration.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RegistrationId implements Serializable {

	@JsonIgnoreProperties("notifications")
	@OneToOne
    @JoinColumn(name = "student_pcn", referencedColumnName="pcn")
    private Student student;

	@JsonIgnoreProperties({"teachers", "states", "reviews"})
    @OneToOne
    @JoinColumn(name = "course_code", referencedColumnName="code")
    private Course course;

    public RegistrationId() {
    }

	public RegistrationId(Student student, Course course) {
		super();
		this.student = student;
		this.course = course;
	}
    
	public Student getStudent() {
		return student;
	}

	public Course getCourse() {
		return course;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistrationId)) return false;
        RegistrationId that = (RegistrationId) o;
        return Objects.equals(getStudent(), that.getStudent()) &&
                Objects.equals(getCourse(), that.getCourse());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getStudent(), getCourse());
    }
}