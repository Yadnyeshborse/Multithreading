package com.multitreading.Executor.ExcutorSereviceMethods.TaskSumbissionMethod;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Execute_Runnable {
    public static void main(String[] args) {
        ExecutorService excutorr= Executors.newFixedThreadPool(2);
        Runnable task=()->System.out.println("Task executed by "+Thread.currentThread().getName());
        excutorr.execute(task);
        excutorr.shutdown();
//        execute(Runnable task)
//        Executes a given task asynchronously but does not return any result.
    }
}
