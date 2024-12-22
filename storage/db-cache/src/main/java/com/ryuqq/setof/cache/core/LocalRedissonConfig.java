package com.ryuqq.setof.cache.core;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"local"})
@Configuration
public class LocalRedissonConfig {

    @Bean
    public RedissonClient redissonClient(@Value("${spring.data.redis.host}") String redisHost,
                                         @Value("${spring.data.redis.port}") int redisPort) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + redisHost + ":" + redisPort);
        return Redisson.create(config);
    }

}
