package com.springboot.guava.service;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * @Description:
 * @Auther: zrblog
 * @CreateTime: 2019-08-11 10:59
 * @Version:v1.0
 */
@Service
public class GuavaCacheService {

    private final static Logger logger = LoggerFactory.getLogger(GuavaCacheService.class);

    @Autowired
    private LoadingCache<String, String> loadingCache;

    @Cacheable(value = "cacheManager")
    public String getDataFromCacheManager(String key) {
        String value = loadDataFromDb(key);
        return value;
    }

    public String getValueFromLocalCache(String key) {
        try {
            String result = loadingCache.get(key);
            logger.info("从缓存中获取数据------------------{},{}", key, result);
            return result;
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String loadDataFromDb(String key) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
        String value = "key---" + key + ",value---" + simpleDateFormat.format(new Date());
        logger.info("模拟从数据库获取数据------------------{},{}", key, value);
        loadingCache.put(key, value);
        logger.info("往缓存中GuavaCacheManager添加数据");
        return value;
    }

    public String getStat() {
        CacheStats stats = loadingCache.stats();
        String result = JSON.toJSONString(stats);
        logger.info("【统计数据】:" + JSON.toJSONString(stats));
        return result;
    }
}
