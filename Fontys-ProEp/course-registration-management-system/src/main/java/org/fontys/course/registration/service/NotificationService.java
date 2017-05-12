package org.fontys.course.registration.service;

import java.util.List;

import org.fontys.course.registration.model.Notification;
import org.fontys.course.registration.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;
	
	public void AddNotification(Notification notification){
		this.notificationRepository.save(notification);
	}
	
	public void AddNotifications(List<Notification> notifications){
		this.notificationRepository.save(notifications);
	}
}
