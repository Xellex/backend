package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		System.out.println("hoi");
		Person p = new Person();
		p.setFirstName("Job");
		System.out.println(p.firstName);
		System.out.println("hoi");
	}

}
