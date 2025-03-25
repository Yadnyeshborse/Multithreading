package com.multitreading.Executor.ExecutorServiceMethods.TaskSubmissionMethod;

import java.util.List;
import java.util.concurrent.*;

public class invokeAll {
    public static void main(String[] args) {
        // Create thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Define tasks with different durations
        List<Callable<String>> tasks = List.of(
                () -> {
                    Thread.sleep(3000);
                    System.out.println("Task 1 completed in " + Thread.currentThread().getName());
                    return "Task 1";
                },
                () -> {
                    Thread.sleep(1000);
                    System.out.println("Task 2 completed in " + Thread.currentThread().getName());
                    return "Task 2";
                },
                () -> {
                    Thread.sleep(2000);
                    System.out.println("Task 3 completed in " + Thread.currentThread().getName());
                    return "Task 3";
                }
        );

        try {
            System.out.println("Submitting all tasks...");

            // Submit all tasks and wait for completion
            List<Future<String>> results = executor.invokeAll(tasks);

            System.out.println("All tasks completed. Processing results...");

            // Process results
            for (Future<String> future : results) {
                try {
                    System.out.println("Result: " + future.get());
                } catch (ExecutionException e) {
                    System.err.println("Task failed: " + e.getCause());
                }
            }

        } catch (InterruptedException e) {
            System.err.println("Tasks were interrupted");
            Thread.currentThread().interrupt(); // Restore interrupt status
        } finally {
            // Shutdown the executor
            executor.shutdown();
            System.out.println("Executor service shutdown");
        }
    }
}