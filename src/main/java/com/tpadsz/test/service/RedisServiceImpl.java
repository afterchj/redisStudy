package com.tpadsz.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service("redisService")
public class RedisServiceImpl implements RedisService {
    private static final Logger log = LoggerFactory.getLogger(RedisServiceImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void append(String key, String str) {
        redisTemplate.opsForValue().append(key, str);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key, 0, -1);
    }
}
