package org.fontys.course.registration.model.cms.component.element;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Text")
public class Text extends Element {

    @Column(columnDefinition = "text")
    private String content;

    public Text() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
