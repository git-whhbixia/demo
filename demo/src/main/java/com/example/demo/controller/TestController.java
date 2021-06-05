package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Create by Hercules
 * 2021-01-15 14:37
 */
@RestController
@RequestMapping("/demo")
public class TestController {

    @ResponseBody
    @RequestMapping(value = "/python", method = RequestMethod.POST)
    public Map python(Long uid) throws Exception {
        System.out.println("uid==" + uid);
        Map map = new HashMap();
        map.put("success", true);
        if (uid == null || uid % 2 == 0) {
            map.put("success", false);
            return map;
        }
        map.put("success", true);
        Map paramMap = new HashMap();
        String s = UUID.randomUUID().toString();
        paramMap.put("user_name", s.substring(0, 4));
        paramMap.put("pass_word", s.substring(5, 10));
        map.put("data", paramMap);
        return map;
    }
}
