package org.fontys.course.registration.service;

import java.util.List;

import org.fontys.course.registration.model.Notification;
import org.fontys.course.registration.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	@Transactional
	public void AddNotification(Notification notification){
		this.notificationRepository.save(notification);
	}

	@Transactional
	public void AddNotifications(List<Notification> notifications){
		this.notificationRepository.save(notifications);
	}
}
