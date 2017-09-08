package com.tpadsz.test.service;

import redis.clients.jedis.ShardedJedis;

/**
 * Created by hongjian.chen on 2017/3/30.
 */
public interface RedisService {
    public ShardedJedis getRedisClient();
    public void returnResource(ShardedJedis shardedJedis);
    public void returnResource(ShardedJedis shardedJedis, boolean broken);
}
