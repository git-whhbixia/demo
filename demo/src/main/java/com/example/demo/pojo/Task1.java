package com.example.demo.pojo;

/**
* Create by Hercules
* 2021-01-14 00:46
*/
public class Task1 implements Runnable {

   private String taskName;

   public Task1(final String taskName) {
       this.taskName = taskName;
   }

   @Override
   public void run() {
       System.out.println("Inside "+taskName);
        throw new RuntimeException("RuntimeException from inside " + taskName);
   }

}
