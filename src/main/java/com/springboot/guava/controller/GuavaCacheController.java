package com.springboot.guava.controller;

import com.springboot.guava.service.GuavaCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Auther: zrblog
 * @CreateTime: 2019-08-11 10:57
 * @Version:v1.0
 */

@Controller
@RequestMapping("/")
public class GuavaCacheController {

    @Autowired
    private GuavaCacheService guavaCacheService;

    @RequestMapping("/get")
    @ResponseBody
    public String getCache(String key) {
        String value = guavaCacheService.getValueFromLocalCache(key);
        return value;
    }

    @RequestMapping("/stat")
    @ResponseBody
    public String getCache() {

        return guavaCacheService.getStat();
    }

    @RequestMapping("/query")
    @ResponseBody
    public String getDataFromCacheManager(String key) {
        return guavaCacheService.getDataFromCacheManager(key);
    }
}
