package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Hercules
 * 2021-06-08 22:39
 */
public class JVMTest {
//-XX:+UseConcMarkSweepGC
//-Xlog:gc+heap=debug:/hprof/mygc.log
//-Xms20m
//-Xmx20m
//-Xmn6m
//-XX:+HeapDumpOnOutOfMemoryError
//-XX:HeapDumpPath=D:/hprof/tcc.hprof

    private byte[] b = new byte[2 * 1024 * 1024];

    public static void main(String[] args) throws InterruptedException {

        try {
            List kList = new ArrayList();
            while (true) {
                kList.add(new JVMTest());
            }
        } catch (Throwable error) {
            error.printStackTrace();
        }
//        System.out.println(Runtime.getRuntime().freeMemory() / 1024.0 / 1024);
//        {
//            byte[] b = new byte[2 * 1024 * 1024];
//        }
//
//        int m = 0;
//        System.gc();
//        Thread.sleep(1000);
//        System.out.println("===================");
//        System.out.println(Runtime.getRuntime().freeMemory() / 1024.0 / 1024);

    }
}
