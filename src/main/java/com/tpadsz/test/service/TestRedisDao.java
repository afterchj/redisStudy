package com.tpadsz.test.service;

import com.tpadsz.test.dao.RedisDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by hongjian.chen on 2017/9/25.
 */
public class TestRedisDao {
    private static ClassPathXmlApplicationContext ctx;

    static {
        ctx = new ClassPathXmlApplicationContext("classpath:conf/applicationContext.xml");
    }

//    private static RedisTemplate redisTemplate = (RedisTemplate) ctx.getBean("redisTemplate");



    public static void main(String[] args) {
//        testMap();
//        testSet();
//        testList();
    }


}
