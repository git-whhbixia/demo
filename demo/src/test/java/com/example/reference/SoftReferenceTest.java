package com.example.reference;

import java.lang.ref.SoftReference;

/**
 * Create by Hercules
 * 2021-02-03 15:58
 */
public class SoftReferenceTest {

    public static void main(String[] args) {
        Object obj = new Object();
        SoftReference softRef = new SoftReference<>(obj);
        obj = null;
        System.gc();
        System.out.println("gc之后的值：" + softRef.get()); // 对象依然存在
    }
}
