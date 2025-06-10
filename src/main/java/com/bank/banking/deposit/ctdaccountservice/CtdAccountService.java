package com.bank.banking.deposit.ctdaccountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.mediatype.hal.CurieProvider;
import org.springframework.hateoas.mediatype.hal.DefaultCurieProvider;
import org.springframework.modulith.Modulithic;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Central application class containing both general application and web
 * configuration as well as a main-method to
 * bootstrap the application using Spring Boot.
 *
 * @see #main(String[])
 * @see SpringApplication
 */
@EnableAsync
@ConfigurationPropertiesScan
@Modulithic
@SpringBootApplication
public class CtdAccountService {

    public static String CURIE_NAMESPACE = "CtdAccountService";

    @Bean
    CurieProvider curieProvider() {
        return new DefaultCurieProvider(CURIE_NAMESPACE, UriTemplate.of("/docs/{rel}.html"));
    }

    /**
     * Bootstraps the application in standalone mode (i.e. java -jar).
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(CtdAccountService.class, args);
    }
}