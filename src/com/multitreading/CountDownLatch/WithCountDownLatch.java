package com.multitreading.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class WithCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread starts.");

        int workerCount = 3;
        CountDownLatch latch = new CountDownLatch(workerCount);

        // Start 3 worker threads
        for (int i = 0; i < workerCount; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " starts working.");
                try {
                    Thread.sleep(1000); // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(Thread.currentThread().getName() + " finishes.");
                latch.countDown(); // Decrement the latch count
            }).start();
        }

        latch.await(); // Main thread waits here until latch count reaches 0
        System.out.println("Main thread ends (after all workers finish!).");
    }
}
