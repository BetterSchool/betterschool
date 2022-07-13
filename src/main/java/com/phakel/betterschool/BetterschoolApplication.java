package com.phakel.betterschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.phakel.betterschool.entity")
@ComponentScan(basePackages = "com.phakel.betterschool.service")
@ComponentScan(basePackages = "com.phakel.betterschool.controller")
@ComponentScan(basePackages = "com.phakel.betterschool.util")
@EnableJpaRepositories(basePackages = "com.phakel.betterschool.repository")
@ConfigurationPropertiesScan(basePackages = "com.phakel.betterschool.config")
public class BetterschoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(BetterschoolApplication.class, args);
    }

}
