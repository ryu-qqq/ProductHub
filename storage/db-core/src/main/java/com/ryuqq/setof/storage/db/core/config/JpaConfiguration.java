package com.ryuqq.setof.storage.db.core.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EntityScan(basePackages = "com.ryuqq.setof.storage.db.core")
@EnableJpaRepositories(basePackages = "com.ryuqq.setof.storage.db.core")
public class JpaConfiguration {

}
