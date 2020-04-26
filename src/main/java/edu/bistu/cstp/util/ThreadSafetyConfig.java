package edu.bistu.cstp.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class ThreadSafetyConfig
{
    private final Map<Integer, Object> goodsLockMap = new ConcurrentHashMap<>();

    private final Object putLock = new Object();

    @Bean
    public Map<Integer, Object> goodsLockMap()
    {
        return goodsLockMap;
    }

    @Bean(name = "putLock")
    public Object putLock()
    {
        return putLock;
    }

}
