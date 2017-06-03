package org.fontys.course.registration.repository;

import java.util.List;

import org.fontys.course.registration.model.Notification;
import org.fontys.course.registration.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
	List<Notification> findByReceiver(Person person);
	
	List<Notification> findTop10ByReceiverOrderByDateDesc(Person person);

	List<Notification> findByIdGreaterThanAndReceiverOrderByDateDesc(Long id, Person person);
}
