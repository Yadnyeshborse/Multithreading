package com.multitreading.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class ErrorHandling {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
                    if (Math.random() > 0.5) throw new RuntimeException("Error!");
                    return "Success";
                })
                .exceptionally(ex -> "Fallback: " + ex.getMessage()) // Handle exception
                .thenAccept(System.out::println); // Print the result

        // Ensure the program waits for async tasks to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
