package org.ug.project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.ug.project2.dao.CoachDaoImpl;
import org.ug.project2.model.Coach;
import org.ug.project2.service.CoachService;

@SpringBootApplication
public class Project2Application {
	public static void main(String[] args) {
		SpringApplication.run(Project2Application.class, args);
	}

}
