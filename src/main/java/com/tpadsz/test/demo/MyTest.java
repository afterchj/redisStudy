package com.tpadsz.test.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.javafx.collections.MappingChange;
import com.tpadsz.test.dao.RedisDao;
import com.tpadsz.test.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.text.SimpleDateFormat;
import java.util.*;

public class MyTest {
    private static ClassPathXmlApplicationContext ctx;

    static {
        ctx = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
    }

    private static RedisTemplate redisTemplate = (RedisTemplate) ctx.getBean("redisTemplate");

    @Test
    public void testEntity() {
//        redisTemplate.delete("testUserList");
        List<User> list = new ArrayList<User>();

        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setId(i);
            user.setName("test" + i);
            user.setPwd("00" + i);
            list.add(user);
        }
//        redisTemplate.opsForValue().set("testEntity", list);
//        redisTemplate.opsForList().leftPushAll("testUserList", list);
//        redisTemplate.opsForValue().set("testUserList", list);
        User user1 = new User();
        user1.setId(121);
        user1.setName("admin");
        user1.setPwd("admin");
        redisTemplate.opsForList().set("testUserList", 1, user1);
        List<User> resultBlackList = redisTemplate.opsForList().range("testUserList", 0, -1);
        for (int i = 0; i < resultBlackList.size(); i++) {
            if (resultBlackList.get(i) instanceof User) {
                System.out.println(resultBlackList.get(i).toString());
                System.out.println("id=" + resultBlackList.get(i).getId() + ",name=" + resultBlackList.get(i).getName() + ",password=" + resultBlackList.get(i).getPwd());
            }
        }
    }

    @Test
    public void testList() {
//        redisTemplate.delete("test");
        List<String> list1 = new ArrayList<String>();
        list1.add("a1");
        list1.add("a2");
        list1.add("a3");

//        redisTemplate.opsForList().leftPushAll("lisKey1", list1);
        List<String> resultList1 = (List<String>) redisTemplate.opsForList().range("lisKey1", 0, -1);
//        redisTemplate.opsForList().set("lisKey1",3,"a4");

        System.out.println(redisTemplate.opsForList().size("lisKey1"));
        System.out.println("resultList=" + resultList1);
        System.out.println("resultList3=" + resultList1.get(3).toString());
    }

    @Test
    public void testRedis() {
        SqlSessionFactory factory = (SqlSessionFactory) ctx.getBean("sqlSessionFactory");
        SqlSession session = factory.openSession();
        List<Map> maps = session.getMapper(RedisDao.class).getByType("hpWeb");
//        for (int i = 0; i < maps.size(); i++) {
//            Map map1 = maps.get(i);
//            System.out.println(map1);
//        }

//        redisTemplate.opsForValue().set("testMap", maps,1,TimeUnit.DAYS);

//        redisTemplate.opsForList().rightPushAll("cpaWeb", maps);
//        redisTemplate.expire("cpaWeb", 10, TimeUnit.MINUTES);
        String result = redisTemplate.opsForValue().get("cpaWeb", 0, -1);
        System.out.println("result=" + result);
        JSONArray array = JSON.parseArray(result);
        if (array != null && array.size() > 0) {
            System.out.println("array=" + array.size());
        }
        List<Map> resultList = (List<Map>) redisTemplate.opsForList().range("hpWeb", 0, -1);
        System.out.println("resultList=" + resultList);
        String jsonStr = JSON.toJSONString(resultList);
        JSONArray jsonArray = JSON.parseArray(jsonStr);
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("jsonArray=" + jsonArray.get(1));
        System.out.println("----------------------------------------------------------------------------------");
        JSONObject jsonObject = JSON.parseObject(jsonArray.get(1).toString());
        JSONArray arr = (JSONArray) JSON.parseObject(jsonObject.get("configs").toString()).get("taskTag");

        System.out.println(arr.get(0) + " " + arr.get(1));
        System.out.println("jsonObject=" + jsonObject.get("configs"));

//        JSONArray array1=JSON.parseArray(jsonObject.get("taskTag").toString());
//        System.out.println("array1=" + array1.get(0)+"array2"+array1.get(1));
        System.out.println("----------------------------------------------------------------------------------");

        List<Map> testJsonMap = JSON.parseArray(jsonStr, Map.class);
        System.out.println("testJsonMap.size=" + testJsonMap);
        for (int i = 0; i < resultList.size(); i++) {
            System.out.println(resultList.get(i).get("configs") + "   " + resultList.get(i).get("detail"));
        }
//        System.out.println("resultList1=" + resultList);
        System.out.println(maps.size());
    }

    @Test
    public void testMap() {
        Map map = new HashMap();
        map.put("id", "123aw21sad231a232313");
        map.put("uid", "aw2121xwwq12121s121");
        map.put("type", "2");
        map.put("imsi", "112121212");
        map.put("createDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        String json = JSON.toJSONString(map);
        System.out.println("json=" + json);
//        redisTemplate.opsForHash().putAll("mapRecord", map);
//        Map<String, String> resultMap = redisTemplate.opsForHash().entries("mapRecord");
//        List<String> reslutMapValues = redisTemplate.opsForHash().values("mapRecord");
//        Set<String> resultMapKeys = redisTemplate.opsForHash().keys("mapRecord");
//        String value = (String) redisTemplate.opsForHash().get("mapRecord", "createDate");
//        System.out.println("value:" + value);
//        System.out.println("resultMapKeys:" + resultMapKeys);
//        System.out.println("resultMap:" + resultMap);
//        System.out.println("reslutMapValues:" + reslutMapValues);
    }

    @Test
    public void testSet() {
        String str = "Hello World";
        System.out.println(str);
//        redisTemplate.opsForValue().set("key03", str);
//        redisTemplate.opsForValue().append("key02", str);
        Object result = redisTemplate.opsForValue().get("testOpsForValues");
        System.out.println("result="+result);
        String key02 = redisTemplate.opsForValue().get("key02", 0, -1);
        System.out.println(redisTemplate.opsForValue().get("key03", 0, -1));
        Set<String> set1 = new HashSet<String>();
        set1.add("set1");
        set1.add("set2");
        set1.add("set3");
//        redisTemplate.opsForSet().difference("helloSet", set1);
//        Set<String> resultSet = redisTemplate.opsForSet().members("testSet");
//        System.out.println("resultSet:" + resultSet);
    }


    @Test
    public void test() {

//        ValueOperations<Serializable, Serializable> opsForValue = redisTemplate.opsForValue();
//        opsForValue.set("mapRecord", "121");
//        System.out.println(opsForValue.get("mapRecord"));
//        redisTemplate.opsForList().remove("framework", 3, "spring");

//        redisTemplate.delete("cic_cache_account_bf51db3e52aa4ddbacf724fcd812b04a");
//        redisTemplate.opsForHash().put("cic_cache_account_bf51db3e52aa4ddbacf724fcd812b04a","avail","100000");
        String str = (String) redisTemplate.opsForHash().get("cic_cache_account_bf51db3e52aa4ddbacf724fcd812b04a", "avail");
//        Map<String,String> values=new HashMap();
//        values.put("name","after");
//        values.put("sex","male");
//        values.put("age","19");
//        redisTemplate.opsForHash().putAll("testHash",values);
        redisTemplate.opsForHash().put("testHash", "cost", "1000");
        Map<String, String> map = redisTemplate.opsForHash().entries("testHash");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        System.out.println("str=" + str + ",result=" + redisTemplate.hasKey("cic_cache_account_bf51db3e52aa4ddbacf724fcd812b04a"));
    }
}
