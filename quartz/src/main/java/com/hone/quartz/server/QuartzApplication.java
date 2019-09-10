package com.hone.quartz.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class QuartzApplication {

	public static final Logger logger = LoggerFactory.getLogger(QuartzApplication.class);

	public static void main(String[] args) {
		logger.info("定时任务主服务 --> 启动！");
		SpringApplication.run(QuartzApplication.class, args);
	}

}
