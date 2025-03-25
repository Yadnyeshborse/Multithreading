package com.multitreading.CountDownLatch;

public class WithoutCountDownLatch {
    public static void main(String[] args) {
        System.out.println("Main thread starts.");

        // Start 3 worker threads
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " starts working.");
                try {
                    Thread.sleep(1000); // Simulate work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " finishes.");
            }).start();
        }

        System.out.println("Main thread ends (may finish before workers!).");
    }
}
