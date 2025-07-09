package com.lopes.producer.appender;

import com.lopes.commons.enums.RedisStreamName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.RedisTemplate;

import static org.springframework.util.StringUtils.hasText;

@Slf4j
@RequiredArgsConstructor
public abstract class RedisStreamAppender<T> {
    private final RedisTemplate<String, Object> redisTemplate;

    protected boolean append(final RedisStreamName streamKey, final T message) {
        final var objectRecord = StreamRecords
                .newRecord()
                .in(streamKey.getName())
                .ofObject(message);

        try {
            final var recordId = redisTemplate.opsForStream().add(objectRecord);

            if (null != recordId) {
                log.debug("Received recordId: {} for message: {}", recordId.getValue(), message);
            } else {
                log.warn("Unable to append message to Redis Stream: {}", message);
            }

            return null != recordId && hasText(recordId.getValue());
        } catch (Exception e) {
            log.error("Error appending message to Redis Stream: {}", message, e);
            return false;
        }
    }

    public abstract boolean append(final T message);
}
