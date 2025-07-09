package com.lopes.consumer.listener;

import com.lopes.commons.enums.RedisStreamName;
import com.lopes.consumer.config.RedisStreamsProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;

import java.time.Duration;
import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
public abstract class RedisStreamListener<T> implements StreamListener<String, ObjectRecord<String, T>> {

    private final RedisStreamsProperties streamsProperties;
    private final RedisTemplate<String, Object> redisTemplate;
    private final Class<T> targetType;

    @PostConstruct
    protected void init() {
        final var options = StreamMessageListenerContainer.StreamMessageListenerContainerOptions
                .builder()
                .pollTimeout(Duration.ofSeconds(streamsProperties.getPollTimeout()))
                .targetType(targetType)
                .build();

        final var container = StreamMessageListenerContainer.create(Objects.requireNonNull(redisTemplate.getConnectionFactory()), options);

        final var instances = getRedisStream().getInstances();

        for (int i = 0; i < instances; i++) {
            final var consumerName = getRedisConsumerName() + "-" + i;
            final var streamOffset = StreamOffset.create(getRedisStreamName(), ReadOffset.lastConsumed());
            final var consumer = Consumer.from(getRedisConsumerGroup(), consumerName);

            log.debug("Starting consumer [{}] with offset [{}]", consumerName, streamOffset);

            container.receive(consumer, streamOffset, this);
        }

        container.start();

        log.info("Started Redis listener for stream: {}", getRedisStreamName());
    }

    @Override
    public void onMessage(final ObjectRecord<String, T> message) {
        try {
            // when a new message arrives, process it
            processMessage(message.getValue());
            // Acknowledge the message after successful processing
            acknowledgeMessage(message);
            // Delete the message after acknowledgment
            deleteMessage(message);
        } catch (Exception e) {
            log.error("Failed to process message: {}", message, e);
        }
    }

    private void acknowledgeMessage(ObjectRecord<String, T> message) {
        // Acknowledge the message
        redisTemplate.opsForStream()
                .acknowledge(getRedisConsumerGroup(), getRedisStreamName(), message.getId());
        log.info("Acknowledged message: {}", message.getId());
    }

    private void deleteMessage(ObjectRecord<String, T> message) {
        Long deletedCount = redisTemplate.opsForStream()
                .delete(getRedisStreamName(), message.getId());
        log.info("Deleted message: {} from stream {}. Deleted count: {}", message.getId(), getRedisStreamName(), deletedCount);
    }

    protected abstract void processMessage(T message);

    protected abstract RedisStreamName getRedisStream();

    private String getRedisStreamName() {
        return getRedisStream().getName();
    }

    private String getRedisConsumerGroup() {
        return getRedisStream().getConsumerGroup();
    }

    private String getRedisConsumerName() {
        return getRedisStream().getConsumerName();
    }
}
