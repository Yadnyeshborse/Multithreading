package com.multitreading.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CombiningMultipleFutures {
    public static void main(String[] args) {
        CompletableFuture<String> userFuture = getUserAsync();
        CompletableFuture<Integer> creditFuture = getCreditScoreAsync();

        userFuture.thenCombine(creditFuture, (user, credit) ->
                String.format("User %s has credit score %d", user, credit)
        ).thenAccept(System.out::println);

        // Ensure the main thread does not exit immediately
        sleep(3);
    }

    // Simulating an async user fetch
    private static CompletableFuture<String> getUserAsync() {
        return CompletableFuture.supplyAsync(() -> {
            sleep(1);
            return "John Doe";
        });
    }

    // Simulating an async credit score fetch
    private static CompletableFuture<Integer> getCreditScoreAsync() {
        return CompletableFuture.supplyAsync(() -> {
            sleep(2);
            return 750;
        });
    }

    // Utility method for simulating delays
    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
