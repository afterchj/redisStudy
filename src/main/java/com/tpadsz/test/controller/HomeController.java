package com.tpadsz.test.controller;

import com.tpadsz.test.utils.DbMySql;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hongjian.chen on 2017/3/30.
 */
@Controller
public class HomeController {
    @RequestMapping("/hello")
    public ModelAndView home() {
        int times = DbMySql.getTaskNum();
        return new ModelAndView("editUI", "message", +times);
    }
    @ResponseBody
    @RequestMapping("/myFresh.do")
    public String testAppend(@RequestBody String params, ModelMap model) {
        int times = DbMySql.getTaskNum();
        System.out.println(times+"，刷新时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return String.valueOf(times);
    }
}
