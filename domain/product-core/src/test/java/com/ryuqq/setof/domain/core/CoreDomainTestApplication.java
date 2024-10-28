package com.ryuqq.setof.domain.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class CoreDomainTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreDomainTestApplication.class, args);
    }

}
