package com.pet.pet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication @EnableEurekaClient
public class PetModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetModuleApplication.class, args);
	}

}
