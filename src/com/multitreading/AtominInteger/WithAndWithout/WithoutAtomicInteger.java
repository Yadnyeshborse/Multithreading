package com.multitreading.AtominInteger.WithAndWithout;

public class WithoutAtomicInteger {
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter++; // Not thread-safe!
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final count: " + counter); // Often < 2000 due to race condition
    }
}

