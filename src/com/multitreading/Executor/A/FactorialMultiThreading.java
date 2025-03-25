package com.multitreading.Executor.A;

public class FactorialMultiThreading {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); // Start time

        Thread[] threads = new Thread[9];

        for (int i = 1; i <= 9; i++) {
            int num = i; // Capture variable for lambda expression
            threads[i - 1] = new Thread(() -> {
                long result = factorial(num);
                System.out.println("Factorial of " + num + " = " + result);
            });
            threads[i - 1].start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join(); // Ensures main thread waits for all child threads
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Total execution time: " + totalTime + " ms");
    }

    private static long factorial(int n) {
        long result = 1;
        for (int j = 1; j <= n; j++) {
            result *= j;
        }
        return result;
    }
}
