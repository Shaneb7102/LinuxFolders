package com.demo.cache;

import redis.clients.jedis.Jedis;

public class RedisBackedCache {
    private final Jedis jedis;

    public RedisBackedCache(Jedis jedis) {
        this.jedis = jedis;
    }

    public void put(String key, String value) {
        this.jedis.set(key, value);
    }

    public String get(String key) {
        return this.jedis.get(key);
    }
}
