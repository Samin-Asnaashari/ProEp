package org.fontys.course.registration.controller;

import java.security.Principal;
import java.util.List;

import org.fontys.course.registration.model.Notification;
import org.fontys.course.registration.service.NotificationService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/notifications")
@Api(
        name = "notification model API",
        description = "provides the list of methods that manages the notification function.",
        stage = ApiStage.RC
)
public class NotificationController {
	
	@Autowired
    private NotificationService notificationService;
	
	@RequestMapping(method = RequestMethod.GET)
    public List<Notification> GetNotificationsForMobileView(Principal principal) throws Exception {
        return this.notificationService.GetNotificationsForMobileView(Integer.valueOf(principal.getName()));
    }
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Notification> GetNotifications(Principal principal) throws Exception {
        return this.notificationService.GetNotifications(Integer.valueOf(principal.getName()));
    }
	
	@RequestMapping(value = "/before/{notificationID}", method = RequestMethod.GET)
    public List<Notification> GetNotificationsBefore(Principal principal,
    		@PathVariable("notificationID") Long notificationID) throws Exception {
        return this.notificationService.GetNotificationsBefore(Integer.valueOf(principal.getName()), notificationID);
    }
	
	@RequestMapping(value = "/after/{notificationID}", method = RequestMethod.GET)
    public List<Notification> GetNotificationsAfter(Principal principal,
    		@PathVariable("notificationID") Long notificationID) throws Exception {
        return this.notificationService.GetNotificationsAfter(Integer.valueOf(principal.getName()), notificationID);
    }
	
	@RequestMapping(value = "/changeStatus/{notificationID}", method = RequestMethod.PUT)
    public void SetNotificationsStatus(@PathVariable("notificationID") Long notificationID) throws Exception {
        this.notificationService.SetNotificationStatus(notificationID);
    }

	//TEST METHODS
	@RequestMapping(value = "/test", method = RequestMethod.POST, consumes = "application/json")
    public void GetNotificationstest(@RequestBody Notification notification) throws Exception {
		this.notificationService.AddNotification(notification);
    }
	
	@RequestMapping(value = "/tests", method = RequestMethod.POST, consumes = "application/json")
    public void GetNotificationstest(@RequestBody List<Notification> notifications) throws Exception {
		this.notificationService.AddNotifications(notifications);
    }
}
