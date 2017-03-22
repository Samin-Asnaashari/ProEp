package org.fontys.course.registration.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Course {

    @Id
    @Column(nullable = false)
    private String code;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Integer EC;

    //TODO check.
    @Column
    private Integer block;

    @Column
    private Integer maxSeats;

    //TODO check.
    @Column
    private Integer filledSeat;

    @Column
    private Date regStartDate;

    @Column
    private Date regEndDate;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE, orphanRemoval = true)
//    private List<Registration> registrations;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE, orphanRemoval = true)
//    private List<CourseState> states;

    public Course() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEC() {
        return EC;
    }

    public void setEC(Integer EC) {
        this.EC = EC;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public Integer getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(Integer maxSeats) {
        this.maxSeats = maxSeats;
    }

    public Integer getFilledSeat() {
        return filledSeat;
    }

    public void setFilledSeat(Integer filledSeat) {
        this.filledSeat = filledSeat;
    }

    public Date getRegStartDate() {
        return regStartDate;
    }

    public void setRegStartDate(Date regStartDate) {
        this.regStartDate = regStartDate;
    }

    public Date getRegEndDate() {
        return regEndDate;
    }

    public void setRegEndDate(Date regEndDate) {
        this.regEndDate = regEndDate;
    }
}
