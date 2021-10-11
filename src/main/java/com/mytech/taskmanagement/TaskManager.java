package com.mytech.taskmanagement;

import com.mytech.taskmanagement.service.NotificationJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TaskManager implements CommandLineRunner{

	@Autowired
	NotificationJobService notificationJobService;
	public static void main(String[] args) {
		SpringApplication.run(TaskManager.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		log.info("Starting sending the notification");
		notificationJobService.processRequest();
		log.info("Finished sending the notification");
	}
}