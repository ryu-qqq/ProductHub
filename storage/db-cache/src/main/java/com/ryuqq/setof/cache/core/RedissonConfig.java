package com.ryuqq.setof.cache.core;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile({"prod"})
@Configuration
public class RedissonConfig {

    @Value("${spring.data.redis.cluster.nodes}")
    private List<String> clusterNodes;

    @Value("${spring.data.redis.password}")
    private String redisPassword;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useClusterServers()
                .addNodeAddress(clusterNodes.stream().map(node -> "redis://" + node).toArray(String[]::new));

        if (redisPassword != null && !redisPassword.isEmpty()) {
            config.useClusterServers().setPassword(redisPassword);
        }

        return Redisson.create(config);
    }

}
