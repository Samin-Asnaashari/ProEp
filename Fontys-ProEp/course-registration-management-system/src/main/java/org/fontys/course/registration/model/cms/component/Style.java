package org.fontys.course.registration.model.cms.component;

import javax.persistence.*;

//TODO improve it.
@Entity
public class Style {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String textColor;

    @Column
    private String backgroundColor;

    @Column
    private Integer paddingTop;

    @Column
    private Integer paddingBottom;

    public Style() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Integer getPaddingTop() {
        return paddingTop;
    }

    public void setPaddingTop(Integer paddingTop) {
        this.paddingTop = paddingTop;
    }

    public Integer getPaddingBottom() {
        return paddingBottom;
    }

    public void setPaddingBottom(Integer paddingBottom) {
        this.paddingBottom = paddingBottom;
    }
}
