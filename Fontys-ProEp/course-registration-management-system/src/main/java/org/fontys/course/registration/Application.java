package org.fontys.course.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.fontys.course.registration.model.PushNotification;
import org.fontys.course.registration.model.Pojo;


@SpringBootApplication
public class Application {
    public static void main(String[] args) throws UnsupportedEncodingException {
        SpringApplication.run(Application.class, args);
        
        //TEST notifications API
//        String postUrl = "https://api.ionic.io/push/notifications";// put in your url
//        Gson gson = new Gson();
//        HttpClient   httpClient    = HttpClientBuilder.create().build();
//        HttpPost     post          = new HttpPost(postUrl);
//        Pojo pojo = new Pojo();
//        pojo.setTokens("eWyabXd5e58:APA91bFpNStDN--z8uJcZ7DwxIxy0M3buAGsgFtqvQyF-9z39iOPU8SsXGdLiXGk3Ne5v19UOeM30ROyIY-fsPR0EN4hvjJqS-75_5_GwaMovEQOtcC8C0N5wOT37jWgyycr4M7kZPNj");
//        pojo.setProfile("pushnotifications");
//        PushNotification notification = new PushNotification();
//        notification.setMessage("from java app");
//        notification.setTitle("Testing");
//        pojo.setNotification(notification);
//        StringEntity postingString = new StringEntity(gson.toJson(pojo));//gson.tojson() converts your pojo to json
//        post.setEntity(postingString);
//        post.setHeader("Content-type", "application/json");
//        post.setHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJlNGE0ZTBmNC1hYzhkLTQzNmUtOTcyMC01Yzk4NjM1MTI3ZGEifQ.sdJ0Wq-2YVDjVWXbi2gA_BzcWjOMH1Arw3mdLVU3sHk");
//        try {
//			HttpResponse  response = httpClient.execute(post);
//			StatusLine s = response.getStatusLine();
//			System.out.println("Status code: " + s.getStatusCode());
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
    }
}
