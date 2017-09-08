package com.tpadsz.test.demo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyTest {
    private static ApplicationContext ctx;

    static {
        ctx = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
    }

    private static RedisTemplate redisTemplate = (RedisTemplate) ctx.getBean("redisTemplate");

    public static void main(String[] args) {
//        testMap();
//        test();
        testSet();
        testList();
    }

    public static void testMap() {
        Map map = new HashMap();
        map.put("id", "123aw21sad231a232313");
        map.put("uid", "aw2121xwwq12121s121");
        map.put("type", "2");
        map.put("imsi", "112121212");
        map.put("createDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        redisTemplate.opsForHash().putAll("mapRecord", map);

        Map<String, String> resultMap = redisTemplate.opsForHash().entries("mapRecord");

        List<String> reslutMapValues = redisTemplate.opsForHash().values("mapRecord");

        Set<String> resultMapKeys = redisTemplate.opsForHash().keys("mapRecord");

        String value = (String) redisTemplate.opsForHash().get("mapRecord", "createDate");
        System.out.println("value:" + value);

        System.out.println("resultMapKeys:" + resultMapKeys);
        System.out.println("resultMap:" + resultMap);
        System.out.println("reslutMapValues:" + reslutMapValues);
    }

    public static void testSet() {
        Set<String> set1 = new HashSet<String>();
        set1.add("set1");
        set1.add("set2");
        set1.add("set3");
        redisTemplate.opsForSet().add("set1", set1);
        Set<String> resultSet = redisTemplate.opsForSet().members("set1");
        System.out.println("resultSet:" + resultSet);
    }

    public static void testList() {
        List<String> list1 = new ArrayList<String>();
        list1.add("a1");
        list1.add("a2");
        list1.add("a3");

        List<String> list2 = new ArrayList<String>();
        list2.add("b1");
        list2.add("b2");
        list2.add("b3");
        redisTemplate.opsForList().leftPush("lisKey1", list1);
        redisTemplate.opsForList().rightPush("listKey2", list2);

        List<String> resultList1 = (List<String>) redisTemplate.opsForList().leftPop("lisKey1");

        List<String> resultList2 = (List<String>) redisTemplate.opsForList().rightPop("listKey2");
        System.out.println("resultList1:" + resultList1);
        System.out.println("resultList2:" + resultList2);
    }

    @Test
    public void test() {

//        ValueOperations<Serializable, Serializable> opsForValue = redisTemplate.opsForValue();
//        opsForValue.set("mapRecord", "121");
//        System.out.println(opsForValue.get("mapRecord"));
//        redisTemplate.opsForList().remove("framework", 3, "spring");

//        redisTemplate.delete("cic_cache_account_bf51db3e52aa4ddbacf724fcd812b04a");
//        redisTemplate.opsForHash().put("cic_cache_account_bf51db3e52aa4ddbacf724fcd812b04a","avail","100000");
        String str= (String) redisTemplate.opsForHash().get("cic_cache_account_bf51db3e52aa4ddbacf724fcd812b04a","avail");
//        Map<String,String> values=new HashMap();
//        values.put("name","after");
//        values.put("sex","male");
//        values.put("age","19");
//        redisTemplate.opsForHash().putAll("testHash",values);
        redisTemplate.opsForHash().put("testHash","cost","1000");
        Map<String,String> map=redisTemplate.opsForHash().entries("testHash");
        for (Map.Entry<String,String> entry:map.entrySet()){
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        System.out.println("str=" + str + ",result=" + redisTemplate.hasKey("cic_cache_account_bf51db3e52aa4ddbacf724fcd812b04a"));
    }
}
