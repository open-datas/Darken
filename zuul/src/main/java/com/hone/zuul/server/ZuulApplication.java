package com.hone.zuul.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * <p>网关服务</p>
 * @author hourz
 * @since 2019-06-10
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulApplication {

	public static final Logger logger = LoggerFactory.getLogger(ZuulApplication.class);

	public static void main(String[] args) {
		logger.info("网关主服务 ---> 启动！");
		SpringApplication.run(ZuulApplication.class, args);
	}

}
