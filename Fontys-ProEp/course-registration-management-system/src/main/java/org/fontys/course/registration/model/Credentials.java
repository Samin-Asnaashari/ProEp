package org.fontys.course.registration.model;

/**
 * Created by Phoenix on 17-May-17.
 */
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
