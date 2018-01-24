package com.mumscheduler.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages="com.mumscheduler")
@EnableJpaRepositories("com.mumscheduler")
@ComponentScan("com.mumscheduler")
@EntityScan("com.mumscheduler")
public class MUMSchedulerApp {
	public static void main(String[] args) {
		SpringApplication.run(MUMSchedulerApp.class, args);
	}
}
