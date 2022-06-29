package com.shopping.controller;

import com.shopping.service.common.RedisUtilService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author bangyuan.zhang
 * @version V1.0
 * @Project: study-springboot
 * @Package com.study.springboot.controller
 * @Description: TODO
 * @date 2022/5/26 16:08
 */
@RestController
@RequestMapping("/redis")
public class RedisTestController {

    @Resource
    private RedisUtilService<String,String> redisUtilService;

    @PostMapping("/test01")
    public void   test01(@RequestParam String str1, @RequestParam String str2){
        redisUtilService.redisPut(str1,str2);
    }

    @PostMapping("/test02")
    public String   test02(String str1){
        String s = redisUtilService.redisGet(str1);
        System.out.println(s);
        return s;
    }

    @PostMapping("/test03")
    public void  test03(String str1){
        redisUtilService.delete(str1);
    }
}
