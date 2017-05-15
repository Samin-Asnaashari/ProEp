package org.fontys.course.registration.service;

import org.fontys.course.registration.model.enums.CourseType;
import org.fontys.course.registration.model.enums.Major;
import org.springframework.stereotype.Service;

@Service
public class EnumsService {

    public CourseType[] GetCourseTypePossibleValues() {
        return CourseType.values();
    }

    public Major[] GetMajorPossibleValues() {
        return Major.values();
    }
}
