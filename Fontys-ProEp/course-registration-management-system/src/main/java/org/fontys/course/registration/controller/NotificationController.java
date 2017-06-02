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
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Notification> GetNotifications(Principal principal) throws Exception {
		System.out.println("GetNotifications Acessed");
        return this.notificationService.GetNotifications(Integer.valueOf(principal.getName()));
    }
	
	@RequestMapping(value = "/before/{pcn}/{notificationID}", method = RequestMethod.GET)
    public List<Notification> GetNotificationsBefore(@PathVariable("pcn") Integer pcn, 
    		@PathVariable("notificationID") Integer notificationID) throws Exception {
        return this.notificationService.GetNotifications(pcn);
    }
	
	@RequestMapping(value = "/after/{pcn}/{notificationID}", method = RequestMethod.GET)
    public List<Notification> GetNotificationsAfter(@PathVariable("pcn") Integer pcn, 
    		@PathVariable("notificationID") Integer notificationID) throws Exception {
        return this.notificationService.GetNotifications(pcn);
    }
	
	@RequestMapping(value = "/changeStatus", method = RequestMethod.PUT)
    public void SetNotificationsStatus(Principal principal) throws Exception {
        this.notificationService.SetNotificationsStatus(Integer.valueOf(principal.getName()));
    }
}
