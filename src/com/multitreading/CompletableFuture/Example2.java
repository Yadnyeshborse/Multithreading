package com.multitreading.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example2 {
    public static void main(String[] args) {
        CompletableFuture<String> task1 = fetchFromAPI("https://api.example.com/data1");
        CompletableFuture<String> task2 = fetchFromAPI("https://api.example.com/data2");
        CompletableFuture<String> task3 = fetchFromAPI("https://api.example.com/data3");

        CompletableFuture.allOf(task1, task2, task3)
                .thenRun(() -> {
                    String combined = Stream.of(task1, task2, task3)
                            .map(CompletableFuture::join) // Join to get the actual values
                            .collect(Collectors.joining(", "));

                    System.out.println("All results: " + combined);
                }).join(); // Ensures the main thread waits for the async tasks to complete
    }

    // Simulated API call with CompletableFuture
    private static CompletableFuture<String> fetchFromAPI(String url) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000); // Simulate network delay
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Response from " + url;
        });
    }
}
