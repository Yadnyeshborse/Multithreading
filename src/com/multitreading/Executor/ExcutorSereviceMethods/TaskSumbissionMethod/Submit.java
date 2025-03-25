package com.multitreading.Executor.ExcutorSereviceMethods.TaskSumbissionMethod;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Submit {
    public static void main(String[] args) {
        ExecutorService excutor= Executors.newFixedThreadPool(2);

        //Executes a task asynchronously and returns a Future<?>, which can be used to track completion.
        for (int i=1;i<=5;i++){
            final  int taskId=i;
            excutor.submit(()->{
                System.out.println("Executing task "+taskId+" by"+Thread.currentThread().getName());
            });
        }
        excutor.shutdown();
    }
}
