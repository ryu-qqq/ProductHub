package com.ryuqq.setof.api.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@ConfigurationPropertiesScan
@SpringBootApplication(scanBasePackages = {
        "com.ryuqq.setof.domain",
        "com.ryuqq.setof.api",
        "com.ryuqq.setof.batch",
        "com.ryuqq.setof.support.utils",
        "com.ryuqq.setof.support.external.core",
        "com.ryuqq.setof.db.core",
        "com.ryuqq.setof.cache.core",
})
public class ProductHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductHubApplication.class, args);
    }

}
