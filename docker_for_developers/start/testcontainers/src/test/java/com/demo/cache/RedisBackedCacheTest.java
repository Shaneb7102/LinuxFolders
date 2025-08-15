package com.demo.cache;

import org.junit.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import redis.clients.jedis.Jedis;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RedisBackedCacheTest {
    @Rule
    public GenericContainer redis = new GenericContainer<>("redis:8.2")
            .withExposedPorts(6379);

    @Rule
    public GenericContainer alpine =
            new GenericContainer<>("alpine:3.22.1")
                    .withCommand("sleep", "10") // simple, no busy-loop
                    .waitingFor(Wait.forLogMessage(".*", 1)); // don't wait for ports

    private RedisBackedCache cache;

    @Before
    public void setUp() throws Exception {
        Jedis jedis = new Jedis(redis.getContainerIpAddress(),
                redis.getMappedPort(6379));
        cache = new RedisBackedCache(jedis);
    }

    @Ignore
    @Test
    public void testFindingInsertedItem(){
        cache.put("foo", "FOO");
        String foundString = cache.get("foo");

        assertTrue("Value for a given key exists", foundString != null);
        assertEquals("Correct Value retrieved from cache", "FOO", foundString);
    }


    @Test
    public void testAlpineContainerEchoCommand() throws IOException, InterruptedException {
        // No ports involved; we just exec in the running Alpine container
        String output = alpine.execInContainer("ls", "/").getStdout();
        assertTrue("Root directory should contain /bin", output.contains("bin"));

        String echo = alpine.execInContainer("echo", "Hello, Testcontainers!").getStdout().trim();
        assertEquals("Hello, Testcontainers!", echo);
    }


}


