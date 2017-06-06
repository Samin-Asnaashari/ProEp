package org.fontys.course.registration.service;

import java.util.List;

import org.fontys.course.registration.model.Notification;
import org.fontys.course.registration.model.Person;
import org.fontys.course.registration.model.enums.NotificationStatus;
import org.fontys.course.registration.model.enums.SendStatus;
import org.fontys.course.registration.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private UtilService utilService;
	
	@Transactional
	public void AddNotification(Notification notification){
		this.notificationRepository.save(notification);
		this.utilService.IncreaseNotificationBadgeCount(notification.getReceiver());
	}

	@Transactional
	public void AddNotifications(List<Notification> notifications){
		this.notificationRepository.save(notifications);
		for (Notification item : notifications) {
			this.utilService.IncreaseNotificationBadgeCount(item.getReceiver());
		}
	}
	
	@Transactional
	public List<Notification> GetNotificationsForMobileView(Integer pcn){
		List<Notification> notifications = this.notificationRepository.findTop5ByReceiverOrderByIdDesc(this.utilService.GetPersonById(pcn));
		for (int i = 0; i < notifications.size(); i++) {
			if(notifications.get(i).getSendStatus().equals(SendStatus.UNSEND)) {
				notifications.get(i).setSendStatus(SendStatus.SEND);
			}
		}
		return notifications;
	}

	@Transactional
	public List<Notification> GetNotificationsBefore(Integer pcn, Long notificationID) {
		List<Notification> notifications = this.notificationRepository.findByIdGreaterThanAndReceiverOrderByIdDesc
				(notificationID, this.utilService.GetPersonById(pcn));
		for (int i = 0; i < notifications.size(); i++) {
			if(notifications.get(i).getSendStatus().equals(SendStatus.UNSEND)) {
				notifications.get(i).setSendStatus(SendStatus.SEND);
			}
		}
		return notifications;
	}
	
	@Transactional
	public List<Notification> GetNotificationsAfter(Integer pcn, Long notificationID){
		List<Notification> notifications = this.notificationRepository.findTop5ByIdLessThanAndReceiverOrderByIdDesc
				(notificationID, this.utilService.GetPersonById(pcn));
		for (int i = 0; i < notifications.size(); i++) {
			if(notifications.get(i).getSendStatus().equals(SendStatus.UNSEND)) {
				notifications.get(i).setSendStatus(SendStatus.SEND);
			}
		}
		return notifications;
	}

	@Transactional
	public void SetNotificationStatus(Long notificationID) {
		this.notificationRepository.findOne(notificationID).setStatus(NotificationStatus.READ);
	}
	
	@Transactional
	public List<Notification> GetUnSendNotifications(Person person) {
		List<Notification> notifications = this.notificationRepository.findByReceiverAndSendStatusOrderByIdDesc(person, SendStatus.UNSEND);
		for (int i = 0; i < notifications.size(); i++) {
			notifications.get(i).setSendStatus(SendStatus.SEND);
		}
		return notifications;
	}
}
