package com.example.demo;

import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.map.LinkedMap;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Create by Hercules
 * 2021-01-27 23:17
 */
public class Test7 {

    public static void main(String[] args) {
//        LinkedMap map = new LinkedMap();
//        map.put(123,234);
//        map.put(87,234);
//        map.put(22,234);
//        map.put(33,234);
//        map.put(44,2345);


        List<Long> contentIdList = Arrays.asList(999l,12l, 13l, 15l);
        List<Integer> scoreList = Arrays.asList(999,121, 131, 151);

        LinkedHashMap<Long, Integer> distinct = distinct(contentIdList, scoreList);

        for (Map.Entry<Long, Integer> map : distinct.entrySet()) {
            System.out.println(map.getKey() + "," + map.getValue());
        }
    }


    private static LinkedHashMap<Long, Integer> distinct(List<Long> contentIdList, List<Integer> scoreList) {
        LinkedHashMap<Long, Integer> resultMap = new LinkedHashMap<>();
        if (CollectionUtils.isEmpty(contentIdList) || CollectionUtils.isEmpty(scoreList)) {
            return resultMap;
        }
        for (int i = 0; i < contentIdList.size(); i++) {
            Long contentId = contentIdList.get(i);
            if (resultMap.containsKey(contentId)) {
                continue;
            }
            resultMap.put(contentId, scoreList.get(i));
        }
        return resultMap;
    }
}
