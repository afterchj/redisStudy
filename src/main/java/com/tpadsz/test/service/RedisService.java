package com.tpadsz.test.service;

import redis.clients.jedis.ShardedJedis;

/**
 * Created by hongjian.chen on 2017/3/30.
 */
public interface RedisService {
     ShardedJedis getRedisClient();
     void returnResource(ShardedJedis shardedJedis);
     void returnResource(ShardedJedis shardedJedis, boolean broken);
}
