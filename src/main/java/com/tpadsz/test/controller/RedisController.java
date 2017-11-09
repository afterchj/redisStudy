package com.tpadsz.test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tpadsz.test.entity.User;
import com.tpadsz.test.entity.Values;
import com.tpadsz.test.service.RedisServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


/**
 * Created by hongjian.chen on 2017/3/30.
 */
@Controller
public class RedisController {
    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);
    @Resource
    RedisServiceImpl redisService;

    @ResponseBody
    @RequestMapping("setString.do")
    public String testString(@RequestBody String params, ModelMap model) {
        System.out.println(params);
        Values map = JSON.parseObject(params, Values.class);
        redisService.set(map.getKey(), map.getValue());
        String msg = redisService.get(map.getKey());
        System.out.println("key=" + msg);
        model.put("msg", msg);
        JSONObject json=new JSONObject();
        json.put("result",msg);
        return JSON.toJSONString(json);
    }

    @ResponseBody
    @RequestMapping("append.do")
    public String testAppend(@RequestBody String params, ModelMap model) {
        System.out.println(params);
        Values map = JSON.parseObject(params, Values.class);
        redisService.append(map.getKey(), map.getValue());
        String msg = redisService.get(map.getKey());
        System.out.println("key=" + msg);
        model.put("msg", msg);
        JSONObject json=new JSONObject();
        json.put("result",msg);
        return JSON.toJSONString(json);
    }

    public static void main(String[] args) {
        String str="{\"id\":\"123\",\"name\":\"test \",\"pwd\":\"test01 \"}";
        User map = JSON.parseObject(str, User.class);
        System.out.println(map.getName()+"   "+map.getPwd());
    }
}
