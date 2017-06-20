package org.fontys.course.registration.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.fontys.course.registration.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;

@Component
public class PushNotificationSender {

	@Autowired
	private UtilService utilService;

	private List<PushNotificationPostParams> pushNotificationPostRequestsNotSent;
	private String postUrl = "https://api.ionic.io/push/notifications";
	private Gson gson;
	private HttpClient httpClient;
	private HttpPost post;

	private PushNotificationSender() {
		super();
		this.pushNotificationPostRequestsNotSent = new ArrayList<>();
		this.gson = new Gson();
	}

	private boolean SendPushNotification(PushNotificationPostParams pushNotificationPostParams) {
		try {
			this.httpClient = HttpClientBuilder.create().build();
			this.post = new HttpPost(postUrl);
			post.setHeader("Content-type", "application/json");
			post.setHeader("Authorization",
					"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJlNGE0ZTBmNC1hYzhkLTQzNmUtOTcyMC01Yzk4NjM1MTI3ZGEifQ.sdJ0Wq-2YVDjVWXbi2gA_BzcWjOMH1Arw3mdLVU3sHk");
			StringEntity postingString = new StringEntity(gson.toJson(pushNotificationPostParams));
			post.setEntity(postingString);
			
			HttpResponse response = httpClient.execute(post);
			StatusLine s = response.getStatusLine();
			if (s.getStatusCode() != 201)
				return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	private List<Person> GetDistinctSendersPcns(List<Notification> notifications) {
		List<Integer> sendersPcns = new ArrayList<>();
		List<Person> senders = new ArrayList<>();
		for (int i = 0; i < notifications.size(); i++) {
			Person person = notifications.get(i).getSender();
			if(!sendersPcns.contains(person.getPcn())) {
				sendersPcns.add(person.getPcn());
				senders.add(person);
			}
		}
		return senders;
	}
	
	private String GetPushNotificationMessageSenders(List<Person> senders) {
		String persons = "";
		if (senders.size() == 1) {
			return senders.get(0).getFirstName() + ".";
		} else {
			for (int i = 0; i < senders.size(); i++) {
				if (i == senders.size() - 1) {
					persons += "and " + senders.get(i).getFirstName() + ".";
				} else {
					persons += senders.get(i).getFirstName() + ", ";
				}
			}
		}
		return persons;
	}

	@Scheduled(initialDelay = 60000, fixedDelay = 120000)
	private void SendFailedPushNotificationPostRequests() {
		for (int i = 0; i < this.pushNotificationPostRequestsNotSent.size(); i++) {
			PushNotificationPostParams pushNotificationPostParams = this.pushNotificationPostRequestsNotSent.get(i);
			if (this.SendPushNotification(pushNotificationPostParams)) {
				this.pushNotificationPostRequestsNotSent.remove(pushNotificationPostParams);
			}
		}
	}

	@Scheduled(initialDelay = 0, fixedDelay = 10000)
	private void SendPushNotifications() {
		try {
			List<Student> students = this.utilService.GetAllStudents();
			for (int i = 0; i < students.size(); i++) {
				Student student = students.get(i);
				List<Notification> unSendNotifications = PushNotificationSender.this.utilService
						.GetUnSendNotifications(student);

				if (unSendNotifications.size() != 0) {
					String senders = this
							.GetPushNotificationMessageSenders(this.GetDistinctSendersPcns(unSendNotifications));

					PushNotificationPostParams pushNotificationPostParams = new PushNotificationPostParams(
							student.getPushNotificationToken(),
							new PushNotification("You have new notifications from: " + senders));

					if (!this.SendPushNotification(pushNotificationPostParams)) {
						this.pushNotificationPostRequestsNotSent.add(pushNotificationPostParams);
					}
				}
				Thread.sleep(1000);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}