package com.lopes.producer.config;

import com.lopes.commons.enums.RedisStreamName;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.RedisTemplate;

import static java.util.Optional.ofNullable;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RedisStreamInitializer {

    private final RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void initializeStreams() {
        RedisStreamName.getStreamNames().forEach(this::initializeStream);
    }

    private void initializeStream(final RedisStreamName stream) {
        final var streamKey = stream.getName();
        final var group = stream.getConsumerGroup();

        try {
            final var ops = redisTemplate.opsForStream();
            final var streamSize = ofNullable(ops.size(streamKey)).orElse(0L);

            log.info("{} - Stream size: {}", streamKey, streamSize);
            if (streamSize == 0) {
                ops
                        .add(StreamRecords.newRecord()
                                .ofObject("init")
                                .withStreamKey(streamKey));
                ops.trim(streamKey, 0L);

                log.info("Initialized stream: {}", streamKey);
            }

            ops.createGroup(streamKey, group);
            log.info("Consumer group '{}' created for stream: {}", group, streamKey);
        } catch (Exception e) {
            log.warn("Consumer group '{}' might already exist or an error occurred for stream '{}'. Error: {}", group, streamKey, e.getMessage());
        }
    }
}
