package com.ryuqq.setof.producthub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.ryuqq.setof.domain", "com.ryuqq.setof.producthub", "com.ryuqq.setof.storage"})
public class ProductHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductHubApplication.class, args);
    }

}
