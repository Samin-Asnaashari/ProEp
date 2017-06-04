package org.fontys.course.registration.service;

import java.util.List;

import org.fontys.course.registration.model.Notification;
import org.fontys.course.registration.model.Person;
import org.fontys.course.registration.model.Student;
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
	}

	@Transactional
	public void AddNotifications(List<Notification> notifications){
		this.notificationRepository.save(notifications);
	}
	
	@Transactional
	public List<Notification> GetNotificationsForMobileView(Integer pcn){
		List<Notification> notifications = this.notificationRepository.findByReceiverOrderByDateDesc(this.utilService.GetPersonById(pcn));
		Integer lastUNREADIndex = 0;
		for (int i = 0; i < notifications.size(); i++) {
			if(notifications.get(i).getStatus().equals(NotificationStatus.UNREAD)){
				lastUNREADIndex = i;
			}
			if(notifications.get(i).getSendStatus().equals(SendStatus.UNSEND)) {
				notifications.get(i).setSendStatus(SendStatus.SEND);
			}
		}
		
		if(notifications.size() < 5){
			return notifications;
		}
		else
			return notifications.subList(0, lastUNREADIndex + 1);
		
	}

	@Transactional
	public void SetNotificationsStatus(Integer pcn) {
		List<Notification> notifications = this.notificationRepository.findByReceiver(this.utilService.GetPersonById(pcn));
		for (int i = 0; i < notifications.size(); i++) {
			if(notifications.get(i).getStatus().equals(NotificationStatus.UNREAD)){
				notifications.get(i).setStatus(NotificationStatus.READ);
			}
		}
	}

	@Transactional
	public List<Notification> GetNotificationsBefore(Integer pcn, Long notificationID) {
		List<Notification> notifications = this.notificationRepository.findByIdGreaterThanAndReceiverOrderByDateDesc
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
		List<Notification> notifications = this.notificationRepository.findTop1ByIdLessThanAndReceiverOrderByDateDesc
				(notificationID, this.utilService.GetPersonById(pcn));
		for (int i = 0; i < notifications.size(); i++) {
			if(notifications.get(i).getSendStatus().equals(SendStatus.UNSEND)) {
				notifications.get(i).setSendStatus(SendStatus.SEND);
			}
		}
		return notifications;
	}

	@Transactional
	public Long getAmountOfBadges(Integer pcn) {
		List<Notification> notifications = this.notificationRepository.findByReceiver(this.utilService.GetPersonById(pcn));
		Long amountOfBadges = (long) 0;
		for (int i = 0; i < notifications.size(); i++) {
			if(notifications.get(i).getStatus().equals(NotificationStatus.UNREAD)){
				amountOfBadges++;
			}
		}
		return amountOfBadges;
	}

	@Transactional
	public void SetNotificationStatus(Long notificationID) {
		this.notificationRepository.findOne(notificationID).setStatus(NotificationStatus.READ);
	}
	
	@Transactional
	public List<Notification> GetUnSendNotifications(Person person) {
		return this.notificationRepository.findByReceiverAndSendStatusOrderByDateDesc(person, SendStatus.UNSEND);
	}

	@Transactional
	public void SetNotificationsSenderStatus(Student student) {
		List<Notification> notifications = this.notificationRepository.findByReceiverAndSendStatusOrderByDateDesc(student, SendStatus.UNSEND);
		for (int i = 0; i < notifications.size(); i++) {
			notifications.get(i).setSendStatus(SendStatus.SEND);
		}
	}
}
