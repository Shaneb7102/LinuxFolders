package com.demo.cache;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RedisBackedCacheTest {
    @Rule
    public GenericContainer redis = new GenericContainer("redis:3.0.6");


    private RedisBackedCache cache;

    @Before
    public void setUp() throws Exception {
        Jedis jedis = new Jedis(redis.getContainerIpAddress(), redis.getMappedPort(6379));

        cache = new RedisBackedCache(jedis);
    }

    @Test
    public void testFindingAnInsertedValue() {
        cache.put("foo", "FOO");
        String foundString = cache.get("foo");

        assertTrue("When an object in the cache is retrieved, it can be found",
                foundString!= null);
        assertEquals("When we put a String in to the cache and retrieve it, the value is the same",
                "FOO", foundString);
    }

    @Test
    public void testNotFindingAValueThatWasNotInserted() {
        String foundObject = cache.get("bar");

        assertFalse("When an object that's not in the cache is retrieved, nothing is found",
                foundObject == "bar");
    }}
