package com.netease.dy.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author hzdingyong
 * @version 2014年8月14日
 */
public class NormalController extends AbstractController implements Controller {
    private static final Logger logger = Logger.getLogger(NormalController.class);
    private final static Pattern p = Pattern.compile("^(.+)\\.html(;.+)?");// 第一次请求的时候会附一个;jsessionid=??

    /**
     * 直接返回.html以前的东西
     */
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uri = request.getRequestURI();

        String destiny = null;
        Matcher m = p.matcher(uri);
        if (m.matches()) {
            destiny = m.group(1);
        } /*else if (uri.equals("/")) {// 这个为什么没有被自动转成 /index.html呢？welcome-file-list不是这么用的？
            destiny = "/index";
          }*/else {
            logger.warn("page not found!!!!! uri=" + uri);
            destiny = "/common/404";
        }
        Map<String, Object> model = new HashMap<String, Object>();
        return new ModelAndView(destiny, model);
    }

    public static void main(String[] args) {
        String[] inputs = new String[] {"/index.html", "/index.html123", "/index.html?fdsff", // getUri的时候不会把问号后面的跟上。。所以没问题
        "/login.html;jsessionid=1phmth6dse41l"};
        for (String input : inputs) {
            Matcher m = p.matcher(input);
            if (m.matches()) {
                System.out.println("true " + m.group(1));
            } else {
                System.out.println("false" + input);
            }
        }
    }
}
