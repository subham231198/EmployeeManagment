package com.example.EmployeeManagement.EmployeeManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.EmployeeManagement.EmployeeManagement.Entity")
@EnableJpaRepositories(basePackages = "com.example.EmployeeManagement.EmployeeManagement.Repository")
@ComponentScan(basePackages = {"com.example.EmployeeManagement.EmployeeManagement.Controller", "com.example.EmployeeManagement.EmployeeManagement.Service"})
public class EmployeeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

}
