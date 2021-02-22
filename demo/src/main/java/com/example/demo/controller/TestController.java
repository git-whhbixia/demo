package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

/**
 * Create by Hercules
 * 2021-01-15 14:37
 */
@RestController
@RequestMapping("/demo")
public class TestController {


    @RequestMapping("/test")
    public void test(int a) throws Exception {
        test1(a);
    }


    public void test1(Integer a) throws Exception {
        System.out.println(a);
    }

    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    @ResponseBody
    public void selfIMCallBack(@RequestParam(name = "app_id", required = false) String appId) {
        System.out.println(appId);
    }
}
