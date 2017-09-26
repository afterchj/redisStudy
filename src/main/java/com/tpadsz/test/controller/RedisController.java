package com.tpadsz.test.controller;

import com.tpadsz.test.service.RedisServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.ShardedJedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hongjian.chen on 2017/3/30.
 */
//@Controller
public class RedisController {
    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);
    @Resource
    RedisServiceImpl redisService;
    @RequestMapping("setString.do")
    public void testString(HttpServletRequest req, HttpServletResponse rep,String key, String value)throws IOException {
        ShardedJedis shardedJedis = redisService.getRedisClient();
        shardedJedis.set(key, value);
        rep.getWriter().print(true);
        req.getSession().setAttribute("result", key + "：" + value);
       // return new ModelAndView("ok", "result", key + "：" + value);
    }

    @RequestMapping("append.do")
    public void testAppend(HttpServletRequest req, HttpServletResponse rep, String key, String value)throws IOException {
        ShardedJedis shardedJedis = redisService.getRedisClient();
        shardedJedis.append(key, value);
        rep.getWriter().print(true);
        req.getSession().setAttribute("result", key + "：" + value);
       // return "ok";
    }
}
