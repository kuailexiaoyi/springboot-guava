package com.springboot.guava.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Auther: zrblog
 * @CreateTime: 2019-08-11 11:17
 * @Version:v1.0
 */

@Configuration
public class GuavaCacheConfig {
    private final static Logger logger = LoggerFactory.getLogger(GuavaCacheConfig.class);

    @Bean
    public CacheBuilder getCacheBuilder() {
        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .recordStats()
                .removalListener(notification -> {
                    System.out.println(notification.getKey() + " " + notification.getValue() + " 被移除,原因:" + notification.getCause());
                });
        return cacheBuilder;
    }

    @Bean
    public CacheManager getCacheManager() {
        GuavaCacheManager cacheManager = new GuavaCacheManager();
        cacheManager.setCacheBuilder(getCacheBuilder());
        return cacheManager;
    }
}
