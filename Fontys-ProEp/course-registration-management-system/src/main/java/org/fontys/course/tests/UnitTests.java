package org.fontys.course.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import org.fontys.course.registration.model.Student;
import org.fontys.course.registration.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootConfiguration
public class UnitTests {
	
	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository repository;

    @Test
    public void testExample() throws Exception {
    	Student student = new Student();
    	student.setFirstName("Agnes");
        this.entityManager.persist(student);
        Student user = this.repository.findOne(37272);
//        assertThat(user.getFirstName()).isEqualTo("Agnes");
//        assertThat(user.getVin()).isEqualTo("1234");
    }
}
