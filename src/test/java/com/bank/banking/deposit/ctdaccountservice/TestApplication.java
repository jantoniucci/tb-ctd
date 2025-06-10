package com.bank.banking.deposit.ctdaccountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

import com.bank.banking.deposit.ctdaccountservice.CtdAccountService;

@TestConfiguration
public class TestApplication {

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgres() {
		return new PostgreSQLContainer<>("postgres:latest");
	}

	public static void main(String[] args) {
		SpringApplication.from(CtdAccountService::main)
				.with(TestApplication.class)
				.run(args);
	}
}