package com.springboot.guava.controller;

import com.springboot.guava.service.LoadingCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Auther: zrblog
 * @CreateTime: 2019-08-11 15:17
 * @Version:v1.0
 */
@Controller
public class LoadingCacheController {

    @Autowired
    private LoadingCacheService loadingCacheService;

    @RequestMapping("/load")
    @ResponseBody
    public String getValue(String key) {
        String value = loadingCacheService.getValue(key);
        return value;
    }
}
