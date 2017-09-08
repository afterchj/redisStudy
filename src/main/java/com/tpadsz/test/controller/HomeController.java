package com.tpadsz.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by hongjian.chen on 2017/3/30.
 */
@Controller
public class HomeController {
    @RequestMapping("/hello")
    public ModelAndView home(){
        return new ModelAndView("editUI","message","修改数据");
    }
}
