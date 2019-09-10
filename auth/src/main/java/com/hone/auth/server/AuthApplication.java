package com.hone.auth.server;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <p>权限中心</p>
 * @author hourz
 * @since 2019-06-10
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(value = "com.hone.dao.auth")
public class AuthApplication {

	public static final Logger logger = LoggerFactory.getLogger(AuthApplication.class);

	public static void main(String[] args) {
		logger.info("用户权限中心 ---> 启动！");
		SpringApplication.run(AuthApplication.class, args);
	}

}
