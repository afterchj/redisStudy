package com.tpadsz.test;

import java.security.Key;
import java.util.*;

import com.tpadsz.test.entity.User;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;


public class MyTestRedis {
    private Jedis jedis;

    @Before
    public void setup() {
        //连接redis服务器，192.168.0.100:6379
        jedis = new Jedis("127.0.0.1", 6379);
        //权限认证
        //jedis.auth("hongjian");
    }

    /**
     * redis存储字符串
     */
    @Test
    public void testString() {
        //-----添加数据----------
//        jedis.set("password", ".chen");
//        jedis.set("name", "xinxin");//向key-->name中放入了value-->xinxin
//        System.out.println(jedis.get("myname") + "," + jedis.get("password"));//执行结果：
//
//        jedis.append("myname", " is my lover"); //拼接
//        System.out.println(jedis.get("myname"));
//
//        jedis.del("name");  //删除某个键
//        System.out.println(jedis.get("name"));
//        //设置多个键值对
//        jedis.mset("name", "liuling", "age", "23", "qq", "476777XXX");
//        jedis.incr("age"); //进行加1操作
        jedis.set("age", "22");
        System.out.println(jedis.get("name") + "\n" + jedis.get("age") + "\n" + jedis.get("qq"));

    }

    /**
     * redis操作Map
     */
    @Test
    public void testMap() {
        //-----添加数据----------
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("uid", "al121ter");
//        map.put("type", "3");
//        map.put("imei", "766256wqw898");
//        jedis.hmset("homgjian", map);

        //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> user = jedis.hmget("testMap", "name", "age", "qq");
        System.out.println("userList=" + user);

        //删除map中的某个键值
        // jedis.hdel("user", "age");
        System.out.println("hmget=" + jedis.hmget("testMap", "age")); //因为删除了，所以返回的是null
        System.out.println("hlen=" + jedis.hlen("testMap")); //返回key为user的键中存放的值的个数2
        System.out.println("exists=" + jedis.exists("testMap"));//是否存在key为user的记录 返回true
        System.out.println("hkeys=" + jedis.hkeys("testMap"));//返回map对象中的所有key
        System.out.println("hvals=" + jedis.hvals("testMap"));//返回map对象中的所有value
        Iterator<String> iter = jedis.hkeys("testMap").iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.print("key=" + key);
            System.out.println(",value=" + jedis.hmget("testMap", key).get(0));
        }
    }

    /**
     * jedis操作List
     */
    @Test
    public void testList() {
        //开始前，先移除所有的内容
//        jedis.del("testList");
//        jedis.rpush("testList", "spring");
//        jedis.rpush("testList", "struts2");
//        jedis.rpush("testList", "hibernate");
//        jedis.rpush("testList", "mybatis");
//        jedis.rpush("testList", "springmvc");
//        jedis.del("employee");
//        for (int i=0;i<3;i++){
//            jedis.lpush("employee",""+i+"");
//            jedis.lpush("employee","test"+i);
//            jedis.lpush("employee","12"+i);
//        }
        List<String> list = jedis.lrange("employee", 0, -1);
        if (list.size() > 0) {
            for (String employee : list) {
                System.out.println("employee = " + employee);
            }
        }
        System.out.println(jedis.lrange("employee", 0, -1));
    }

    /**
     * jedis操作Set
     */
    @Test
    public void testSet() {
        //添加
//        jedis.sadd("testSet", "liuling");
//        jedis.sadd("testSet", "xinxin");
//        jedis.sadd("testSet", "ling");
//        jedis.sadd("testSet", "zhangxinxin");
//        jedis.sadd("testSet", "who");
//        //移除noname
//        jedis.srem("users", "who");
        Set<String> users = jedis.smembers("testSet");//获取所有加入的value
        Iterator<String> it = users.iterator();
        while (it.hasNext()) {
            String user = it.next();
            System.out.println("user\t" + user);
        }
        System.out.println("usersSet="+users);
        System.out.println(jedis.sismember("testSet", "who"));//判断 who 是否是users集合的元素
        for (int i = 0; i < 3; i++) {
            System.out.println(jedis.srandmember("testSet"));//随机返回member
        }
        System.out.println(jedis.scard("testSet"));//返回集合的元素个数
    }

    @Test
    public void testHash() {
        Map<String, String> hash = new HashMap<String, String>();
        hash.put("username", "after");
        hash.put("password", "123");
        hash.put("age", "23");
        jedis.hmset("myhash", hash);
    }

    @Test
    public void test() throws InterruptedException {
        //jedis 排序
        //注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）
        jedis.del("a");//先清除数据，再加入数据进行测试
        jedis.rpush("a", "1");
        jedis.lpush("a", "6");
        jedis.lpush("a", "3");
        jedis.lpush("a", "9");
        System.out.println(jedis.lrange("a", 0, -1));// [9, 3, 6, 1]
        System.out.println(jedis.sort("a")); //[1, 3, 6, 9]  //输入排序后结果
        System.out.println(jedis.lrange("a", 0, -1));
    }

    @Test
    public void testRedisPool() {
        RedisUtil.getJedis().set("newname", "中文测试");
        System.out.println(RedisUtil.getJedis().get("newname"));
    }
}