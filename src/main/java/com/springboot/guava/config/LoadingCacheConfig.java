package com.springboot.guava.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import com.springboot.guava.service.LoadingCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Auther: zrblog
 * @CreateTime: 2019-08-11 15:09
 * @Version:v1.0
 */
@Configuration
public class LoadingCacheConfig {

    @Autowired
    private LoadingCacheService loadingCacheService;

    @Bean
    public LoadingCache<String, String> getLoadingCache() {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                /** 设置并发级别为8，并发级别是指可以同时写缓存的线程数*/
                .concurrencyLevel(8)
                /*设置缓存容器的初始容量为10*/
                .initialCapacity(10)
                /*设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项*/
                .maximumSize(100)
                /** 设置最大容量：1M*/
                .maximumWeight(1024 * 1024 * 1024)
                /** 当缓存的最大容量接近 设定的最大容量时，采用LRU算法对缓存进行回收*/
                .weigher(new Weigher<String, String>() {
                    @Override
                    public int weigh(String key, String value) {
                        return key.getBytes().length + value.getBytes().length;
                    }
                })
                /*是否需要统计缓存情况,该操作消耗一定的性能,生产环境应该去除*/
                .recordStats()
                /** 设置过期策略：在10分钟内未访问则过期*/
                .expireAfterAccess(10, TimeUnit.MINUTES)
                /** 设置过期策略：数据写入后，10分钟过期*/
                .expireAfterWrite(17, TimeUnit.MINUTES)
                /** 缓存的刷新间隔，当达到刷新缓存间隔，执行CacheLoader的load方法刷新缓存，如下：1分钟刷新一次*/
                .refreshAfterWrite(1, TimeUnit.MINUTES)
                /**设置缓存的移除通知*/
                .removalListener(notification -> {
                    System.out.println(notification.getKey() + " " + notification.getValue() + " 被移除,原因:" + notification.getCause());
                })
                //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key){
                        String value = loadingCacheService.loadDataFromDb(key);
                        return value;
                    }
                });
        return cache;
    }



}
