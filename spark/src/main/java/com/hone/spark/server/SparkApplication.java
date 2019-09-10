package com.hone.spark.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <p>网关服务</p>
 * @author hourz
 * @since 2019-06-10
 */
@SpringBootApplication
@EnableEurekaClient
public class SparkApplication {

	public static final Logger logger = LoggerFactory.getLogger(SparkApplication.class);

	public static void main(String[] args) {
		logger.info("网关主服务 ---> 启动！");
		SpringApplication.run(SparkApplication.class, args);
	}

}
