package com.example;

/**
 * Create by Hercules
 * 2021-06-09 22:01
 */
public class Test1 {


    public static void main(String[] args) {

        int f = 1;
        int e = 10;
        int m = 1;
        System.out.println((f + e) * e / 2);
        while (m < (f + e) * f / 2) {
            m += m;
            m++;
            System.out.println(m);
        }
        System.out.println(m);
    }
}
