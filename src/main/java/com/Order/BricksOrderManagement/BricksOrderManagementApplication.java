package com.Order.BricksOrderManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
		"com.Order.BricksOrderManagement.*"})
public class BricksOrderManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BricksOrderManagementApplication.class, args);
	}

}
