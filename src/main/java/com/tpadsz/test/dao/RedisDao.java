package com.tpadsz.test.dao;



import java.util.List;
import java.util.Map;

/**
 * Created by hongjian.chen on 2017/9/25.
 */
public interface RedisDao {
   List<Map> getByType(String type);
}
