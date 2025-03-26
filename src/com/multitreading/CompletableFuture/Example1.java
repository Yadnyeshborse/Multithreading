package com.multitreading.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class Example1 {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> 10)
                .thenApply(x -> x * 2)               // Sync transformation
                .thenApplyAsync(x -> x + 5)          // Async transformation
                .thenAccept(System.out::println);    // Prints "25"
    }
}
