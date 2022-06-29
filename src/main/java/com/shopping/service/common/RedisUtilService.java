package com.shopping.service.common;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * zby个人原创
 *
 * @author zby
 * @date 2022/06/29 11:00
 **/
public interface RedisUtilService<K,V> {

    void redisPut(K key, V value);

    void redisPutNoTime(K key, V value) ;

    void redisPut(K key, V value, Long timeout, TimeUnit timeUnit);

    V redisGet(K key) ;

    <T> T redisGetObject(K key, Class<T> clazz);

    <T> List<T> redisGetListObject(K key, Class<T> clazz);

    Set<K> redisGetkeysByVagueKey(K vagueKey ) ;

    Long redisDelByVagueKey(K vaguekey) ;

    public void delete(String key);
    /**
     *根热key设置超时时间*ap aram key
     *ap aramtime0utaparamtimeUnit*/
    public void setExpireByKey(K key,long timeOut,TimeUnit timeUnit);
}
