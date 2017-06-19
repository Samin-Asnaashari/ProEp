package org.fontys.course.registration.repository;

import java.util.List;

import org.fontys.course.registration.model.Course;
import org.fontys.course.registration.model.Notification;
import org.fontys.course.registration.model.Person;
import org.fontys.course.registration.model.enums.SendStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
	List<Notification> findByReceiver(Person person);
	
	List<Notification> findTop5ByReceiverOrderByIdDesc(Person person);
	
	List<Notification> findByReceiverOrderByDateDesc(Person person);

	List<Notification> findByIdGreaterThanAndReceiverOrderByIdDesc(Long id, Person person);
	
	List<Notification> findTop5ByIdLessThanAndReceiverOrderByIdDesc(Long id, Person person);
	
	List<Notification> findByReceiverAndSendStatusOrderByIdDesc(Person person, SendStatus status);
	
	Long deleteBySender(Person person);
}
