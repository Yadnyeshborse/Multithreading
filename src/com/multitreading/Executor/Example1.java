package com.multitreading.Executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Example1 {
    public static void main(String[] args) throws ExecutionException,InterruptedException {
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        Future<Integer>  submit=executorService.submit(()->1+2);
        Integer i=submit.get();
        System.out.println("Sum is "+i);
        executorService.shutdown();
        System.out.println(executorService.isTerminated());
    }
}
