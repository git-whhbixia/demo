package com.example.demo;

/**
* Create by Hercules
* 2021-01-14 00:46
*/
public class Task implements Runnable {

   private String taskName;

   public Task(final String taskName) {
       this.taskName = taskName;
   }

   @Override
   public void run() {
       System.out.println("Inside "+taskName);
//        throw new RuntimeException("RuntimeException from inside " + taskName);
   }

}
