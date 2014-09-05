package com.netease.dy.controller;

import org.springframework.stereotype.Service;

@Service(value = "testService")
public class TestService {

    private static int i = 0;

    public TestService() {
        i++;
        System.out.println(this + " init " + i + " times");
    }
}
