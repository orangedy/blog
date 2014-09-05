package com.netease.dy.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

@Controller
public class Test2Controller {

    @Resource
    private TestService testService;

    private static int i = 0;

    public Test2Controller() {
        i++;
        System.out.println(this + " init " + i + " times");
    }

}
