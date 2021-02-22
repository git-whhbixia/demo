package com.example.demo;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Create by Hercules
 * 2021-02-01 10:48
 */
public class Test8 {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int k = ThreadLocalRandom.current().nextInt(10, 12);
            System.out.println(k);
        }
    }
}
