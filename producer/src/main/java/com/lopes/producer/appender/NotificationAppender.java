package com.lopes.producer.appender;

import com.lopes.commons.Notification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import static com.lopes.commons.enums.RedisStreamName.NOTIFICATION_QUEUE;

@Service
public class NotificationAppender extends RedisStreamAppender<Notification> {
    public NotificationAppender(final RedisTemplate<String, Object> redisTemplate) {
        super(redisTemplate);
    }

    @Override
    public boolean append(final Notification message) {
        return append(NOTIFICATION_QUEUE, message);
    }
}
