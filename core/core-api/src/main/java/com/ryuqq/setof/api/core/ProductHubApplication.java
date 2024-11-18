package com.ryuqq.setof.api.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication(scanBasePackages = {

        "com.ryuqq.setof.domain",
        "com.ryuqq.setof.api",
        "com.ryuqq.setof.batch",
        "com.ryuqq.setof.storage.db.core",
        "com.ryuqq.setof.storage.db.cache",
        "com.ryuqq.setof.storage.db.index",


})
public class ProductHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductHubApplication.class, args);
    }

}
