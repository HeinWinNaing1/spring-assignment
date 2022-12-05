package com.hostmdy.ppm;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hostmdy.ppm.domain.Backlog;
import com.hostmdy.ppm.domain.Project;
import com.hostmdy.ppm.domain.User;
import com.hostmdy.ppm.service.ProjectService;
import com.hostmdy.ppm.service.UserService;

@SpringBootApplication
public class PpmtoolApplication implements CommandLineRunner{
	
	@Autowired
	ProjectService projectServie;
	
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(PpmtoolApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Project project1 = new Project("OnlineShop","oshop","This is online shop",LocalDate.now(),LocalDate.of(2023,Month.FEBRUARY,6));
		
		User user = new User("heinwinnaing0@gmail.com","HeinWinNaing","1234");
		
		project1.setUser(user);
		user.getProjects().add(project1);
		
		userService.saveOrUpdateUser(user);
		projectServie.saveOrUpdate(project1);
		
		
		
	}

}
