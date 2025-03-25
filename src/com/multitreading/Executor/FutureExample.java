package com.multitreading.Executor;

import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Submit a Runnable (returns Future<?>)
        Future<?> future = executorService.submit(() -> System.out.println("Hello"));

        // get() will return null for Runnable
        System.out.println("Future.get(): " + future.get()); // prints "null"

        if(future.isDone()) {
            System.out.println("Task is done!");
        }

        executorService.shutdown();
    }
}