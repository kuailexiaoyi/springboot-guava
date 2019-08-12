package com.springboot.guava.config;

import com.google.common.cache.CacheLoader;
import com.springboot.guava.service.GuavaCacheService;
import com.springboot.guava.service.LoadingCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 随机缓存加载,实际使用时应实现业务的缓存加载逻辑,例如从数据库获取数据
 */
@Service
public class DemoCacheLoader extends CacheLoader<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(GuavaCacheService.class);

    @Autowired
    private LoadingCacheService loadingCacheService;

    @Override
    public String load(String key){
        String value = loadingCacheService.loadDataFromDb(key);
        return value;
    }
}