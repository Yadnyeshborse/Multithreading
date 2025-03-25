package com.multitreading.Executor.ExcutorSereviceMethods.TaskSumbissionMethod;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorExample2 {
    public static void main(String[] args) {
        //invoke all example
        // Create thread pool with 2 threads
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Define tasks with proper generic types
        Callable<Integer> callable1 = () -> {
            System.out.println("Task 1 executing in " + Thread.currentThread().getName());
            return 1;
        };

        Callable<Integer> callable2 = () -> {
            System.out.println("Task 2 executing in " + Thread.currentThread().getName());
            return 2;
        };

        Callable<Integer> callable3 = () -> {
            System.out.println("Task 3 executing in " + Thread.currentThread().getName());
            return 3;
        };

        List<Callable<Integer>> tasks = Arrays.asList(callable1, callable2, callable3);

        try {
            // Submit all tasks and get futures
            List<Future<Integer>> futures = executorService.invokeAll(tasks);

            System.out.println("Hello world");

            // Process results
            for (Future<Integer> future : futures) {
                try {
                    Integer result = future.get();
                    System.out.println("Task result: " + result);
                } catch (ExecutionException e) {
                    System.err.println("Task failed: " + e.getCause());
                }
            }

        } catch (InterruptedException e) {
            System.err.println("Tasks were interrupted");
            Thread.currentThread().interrupt(); // Restore interrupt status
        } finally {
            // Shutdown the executor
            executorService.shutdown();
        }
    }
}