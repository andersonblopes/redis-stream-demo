package com.lopes.commons.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum RedisStreamName {
    NOTIFICATION_QUEUE("notification", "notification-consumer", 1);

    private static final List<RedisStreamName> STREAM_NAMES = Arrays.stream(values()).toList();

    private final String name;
    private final String consumerName;
    private final int instances;

    public static List<RedisStreamName> getStreamNames() {
        return STREAM_NAMES;
    }

    public String getConsumerGroup() {
        return consumerName + "-group";
    }

}
