package com.medykowski.RestBank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.medykowski.RestBank.entity")
@EnableJpaRepositories(basePackages = "com.medykowski.RestBank.repository")
public class RestBankApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestBankApplication.class, args);
	}
}