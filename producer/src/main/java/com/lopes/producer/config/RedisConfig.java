package com.lopes.producer.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@RequiredArgsConstructor
@Configuration
public class RedisConfig {
    @Bean
    JedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory();
    }
}
