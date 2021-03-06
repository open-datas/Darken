package com.hone.process.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProcessApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessApplication.class, args);
	}

}
