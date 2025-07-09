package com.lopes.consumer.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(value = "redis.streams")
@Getter
@Setter(value = AccessLevel.MODULE)
public class RedisStreamsProperties {
    long pollTimeout;
}
