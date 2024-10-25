package com.ryuqq.setof.storage.db.core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TestConfiguration
@EnableJpaRepositories(basePackages = "com.ryuqq.setof.storage.db.core")
@EntityScan(basePackages = "com.ryuqq.setof.storage.db.core")
public class StorageModuleTestConfig {


}
