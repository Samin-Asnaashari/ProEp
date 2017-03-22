package org.fontys.course.registration.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.fontys.course.registration.model.enums.Permission;

import javax.persistence.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "userType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Student.class, name = "Student"),
        @JsonSubTypes.Type(value = Teacher.class, name = "Teacher")
})
@Entity
@DiscriminatorColumn(name = "user_type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Person {

    @Id
    @Column(nullable = false)
    private Integer pcn;
    //TODO private Long pcn / private string ipcn

    @Column(nullable = false)
    private String password;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String familyName;

    //TODO Think about it this doesn't feel right maybe having Admin is better.
    @Column
    private Permission permission;

    public Person() {
    }

    public Integer getPcn() {
        return pcn;
    }

    public void setPcn(Integer pcn) {
        this.pcn = pcn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
