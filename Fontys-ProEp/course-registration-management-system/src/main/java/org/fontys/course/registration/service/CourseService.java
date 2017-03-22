package org.fontys.course.registration.service;

import org.fontys.course.registration.repository.CourseRepository;
import org.fontys.course.registration.service.Interface.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    //TODO this is example.
//    @Transactional //only when is needed
//    @Override
//    public Block create(Block block) throws Exception {
//        Style style = new Style();
//        style = this.styleService.create(style);
//        block.setStyle(style);
//        return this.blockRepository.save(block);
//    }
}
