package com.shopping.service.common.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shopping.service.common.RedisUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * zby个人原创
 *
 * @author zby
 * @date 2022/06/29 11:01
 **/
@Service("RedisUtilService")
public class RedisUtilServiceImpl<K, V> implements RedisUtilService<K, V> {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void redisPut(K key, V value) {
        //默认存储时间
        redisPut(key, value, Long.valueOf(60 * 24), TimeUnit.MINUTES);
    }

    @Override
    public void redisPutNoTime(K key, V value) {
        BoundValueOperations<Object, Object> boundValueOps = redisTemplate.boundValueOps(key);
        boundValueOps.set(value);
    }

    @Override
    public void redisPut(K key, V value, Long timeout, TimeUnit timeUnit) {
        BoundValueOperations<Object, Object> boundValueOps = redisTemplate.boundValueOps(key);
        boundValueOps.set(value);
        boundValueOps.expire(timeout, timeUnit);
    }

    @Override
    public V redisGet(Object key) {
        ValueOperations<Object, Object> valueOperations = redisTemplate.opsForValue();
        return (V) valueOperations.get(key);
    }

    /*通过模糊key查出所有的key列表*/
    @Override
    public Set<K> redisGetkeysByVagueKey(K vagueKey) {
        Set<K> keys = redisTemplate.keys(vagueKey);
        return keys;
    }

    /*通过模糊key删除所有的key*/
    @Override
    public Long redisDelByVagueKey(K vaguekey) {
        Set<K> keys = redisGetkeysByVagueKey(vaguekey);
        Long count = redisTemplate.delete(keys);
        return count;
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void setExpireByKey(K key, long timeOut, TimeUnit timeUnit) {
        redisTemplate.expire(key, timeOut, timeUnit);
    }

    @Override
    public <T> List<T> redisGetListObject(K key, Class<T> clazz) {
        V str = redisGet(key);
        if (str == null) {
            return null;
        }
        List<T> list = new ArrayList<T>();
        list = JSONObject.parseArray(JSON.toJSONString(str), clazz);
        return list;
    }

    /*将字符串转化为对象*/
    @Override
    public <T> T redisGetObject(K key, Class<T> clazz) {
        V str = redisGet(key);
        if (str == null) {
            return null;
        }
        String result = "{}";
        if (str instanceof String || str instanceof Integer || str instanceof Long) {
            return (T) str;
        } else {
            result = JSON.toJSON(str).toString();
        }
        JSONObject json = JSONObject.parseObject(result);
        T resObj = JSONObject.toJavaObject(json, clazz);
        return resObj;
    }

    public <T> Map<String, T> redisGetMap0bject(K key, Class<T> clazz) {
        Map<String, T> map = new HashMap<>();
        V str = redisGet(key);
        if (str == null) {
            return map;
        }
        String strString = String.valueOf(str);
        JSONObject json = JSONObject.parseObject(strString);
        for (Map.Entry<String, Object> entry : json.entrySet()) {
            if (clazz.getName().indexOf("String") >= 0) {
                map.put(entry.getKey(), entry.getValue() == null ? null : (T) entry.getValue());
            } else {
                String valuestr = JSONObject.toJSONString(entry.getValue());
                JSONObject valueJson = JSONObject.parseObject(valuestr);
                T res0bj = JSONObject.toJavaObject(valueJson, clazz);
                map.put(entry.getKey(), res0bj);
            }
        }
        return map;
    }
}