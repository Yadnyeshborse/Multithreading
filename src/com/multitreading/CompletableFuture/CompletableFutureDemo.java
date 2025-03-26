package com.multitreading.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Run async task (uses ForkJoinPool.commonPool())
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Hello, CompletableFuture!";
        });

        // Block and get result (or use non-blocking callbacks)
        System.out.println(future.get()); // "Hello, CompletableFuture!"
    }
}
