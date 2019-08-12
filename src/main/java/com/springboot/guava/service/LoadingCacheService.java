package com.springboot.guava.service;

import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * @Description:
 * @Auther: zrblog
 * @CreateTime: 2019-08-11 15:15
 * @Version:v1.0
 */
@Service
public class LoadingCacheService {
    private final static Logger logger = LoggerFactory.getLogger(LoadingCacheService.class);

    @Autowired
    private LoadingCache<String, String> loadingCache;

    public String getValue(String key) {
        String result = null;
        try {
            result = loadingCache.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String loadDataFromDb(String key) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
        String value = "key---" + key + ",value---" + simpleDateFormat.format(new Date());
        logger.info("模拟从数据库获取数据------------------{},{}", key, value);
        loadingCache.put(key, value);
        logger.info("往缓存中LoadingCache添加数据");
        return value;
    }
}
