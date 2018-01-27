package com.mumscheduler.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootApplication(scanBasePackages="com.mumscheduler")
@EnableJpaRepositories("com.mumscheduler")
@ComponentScan("com.mumscheduler")
@EntityScan("com.mumscheduler")
public class MUMSchedulerApp {
	public static void main(String[] args) {
		SpringApplication.run(MUMSchedulerApp.class, args);
	}
	
	/*
	 * This LocalDate handling is inspired by this article
	 * https://github.com/HichamBI/spring-boot-java8-rest
	 */
	@Bean
    @Primary
    public ObjectMapper objectMapper() {
        return new ObjectMapper().findAndRegisterModules();
    }
}
