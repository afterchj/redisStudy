package com.tpadsz.test.demo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by hongjian.chen on 2017/3/30.
 */
public class RedisDemo {

    private static ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:application_Context.xml");
    private static ShardedJedisPool shardedJedisPool = (ShardedJedisPool) ac.getBean("shardedJedisPool");
    @Test
    public void test() {
        System.out.println(shardedJedisPool);
    }
    @Test
    public void testString() {
        ShardedJedis shardJedis = shardedJedisPool.getResource();
        // shardJedis.set("redisDemo","spring+redis");
        boolean flag = shardJedis.getbit("max", 0);
        long result = shardJedis.incrBy("year", 1);
        System.out.println(shardJedis.type("say"));
        System.out.println(result + "\t" + flag);
    }
    //用value替换key的index为offset的字符串。
    @Test
    public void setRange(){
        ShardedJedis shardJedis = shardedJedisPool.getResource();
        String key="testRange";
        long offset=2;
        String value="A";
        long result = shardJedis.setrange(key, offset, value);
        System.out.println(result);
    }
    @Test
    public void getRange(){
        ShardedJedis shardJedis = shardedJedisPool.getResource();
        String key= "testRange";
        long startOffset=0;
        long endOffset=4;
        System.out.println(shardJedis.getrange(key, startOffset, endOffset));
    }
    @Test
    public void getSet(){
        ShardedJedis shardJedis = shardedJedisPool.getResource();
        String key= "myname";
        String value="hongjian.chen";
        System.out.println(shardJedis.getSet(key,value));
    }
}
