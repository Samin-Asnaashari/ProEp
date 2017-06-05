package org.fontys.course.registration.model;


public class Credentials {
    private String password;
    private Integer pcn;

    public Credentials() {
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
}
