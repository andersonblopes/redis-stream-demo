package com.lopes.consumer.listener;

import com.lopes.commons.Notification;
import com.lopes.commons.enums.RedisStreamName;
import com.lopes.consumer.config.RedisStreamsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationListener extends RedisStreamListener<Notification> {

    public NotificationListener(RedisStreamsProperties streamsProperties,
                                RedisTemplate<String, Object> redisTemplate) {
        super(streamsProperties, redisTemplate, Notification.class);
    }

    @Override
    protected void processMessage(final Notification message) {

        log.info("Processing Notification: {}", message);
    }

    @Override
    protected RedisStreamName getRedisStream() {
        return RedisStreamName.NOTIFICATION_QUEUE;
    }
}
