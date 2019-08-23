package com.ease.archiecture.util;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * @author wei.jiang
 * @since 19-8-23 下午5:53
 */
public class RedisUtil {


    public static RedisCommands<String, String> getRedisConnection() {
        RedisClient redisClient = RedisClient.create("redis://localhost");
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        return connect.sync();
    }
}
