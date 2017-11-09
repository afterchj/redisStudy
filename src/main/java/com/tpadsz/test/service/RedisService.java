package com.tpadsz.test.service;


/**
 * Created by hongjian.chen on 2017/3/30.
 */
public interface RedisService {
     void set(String key,String value);
     void append(String key,String str);
     String get(String key);
}
