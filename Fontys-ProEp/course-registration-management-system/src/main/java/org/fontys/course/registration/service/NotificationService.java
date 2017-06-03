package org.fontys.course.registration.service;

import java.util.List;

import org.fontys.course.registration.model.Notification;
import org.fontys.course.registration.model.enums.NotificationStatus;
import org.fontys.course.registration.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
	public List<Notification> GetNotifications(Integer pcn){
		return this.notificationRepository.findTop10ByReceiverOrderByDateDesc(this.utilService.GetPersonById(pcn));
	}

	@Transactional
	public void SetNotificationsStatus(Integer pcn) {
		// TODO Auto-generated method stub
		List<Notification> notifications = this.notificationRepository.findByReceiver(this.utilService.GetPersonById(pcn));
		for (int i = 0; i < notifications.size(); i++) {
			if(notifications.get(i).getStatus().equals(NotificationStatus.UNREAD)){
				notifications.get(i).setStatus(NotificationStatus.READ);
			}
		}
	}

	public List<Notification> GetNotificationsBefore(Integer pcn, Long notificationID) {
		return this.notificationRepository.findByIdGreaterThanAndReceiverOrderByDateDesc(notificationID, this.utilService.GetPersonById(pcn));
	}
}
