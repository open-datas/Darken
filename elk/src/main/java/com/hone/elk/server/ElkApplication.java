package com.hone.elk.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>注册中心和配置中心</p>
 * @author hourz
 * @since 2019-06-12
 */
@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
//@EnableEurekaClient
public class ElkApplication {

	public static final Logger logger = LoggerFactory.getLogger(ElkApplication.class);

	public static void main(String[] args) {
		logger.info("日志分类主服务 --> 启动！");
		SpringApplication.run(ElkApplication.class, args);
	}
}
