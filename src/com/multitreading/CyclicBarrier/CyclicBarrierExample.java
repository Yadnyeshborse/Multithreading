package com.multitreading.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        //CyclicBarrier is a synchronization aid that allows a set of threads to wait for
        // each other to reach a common barrier point before continuing execution. Unlike CountDownLatch,
        // it is reusable—once all threads reach the barrier, it resets for the next use.

        final int RUNNERS = 4;

        // CyclicBarrier with 4 parties + a barrier action (optional)
        CyclicBarrier startingPistol = new CyclicBarrier(RUNNERS, () -> {
            System.out.println("\nAll runners ready! Race starts now!\n");
        });

        // Create and start runner threads
        for (int i = 1; i <= RUNNERS; i++) {
            new Thread(new Runner("Runner-" + i, startingPistol)).start();
        }
    }

    static class Runner implements Runnable {
        private final String name;
        private final CyclicBarrier barrier;

        public Runner(String name, CyclicBarrier barrier) {
            this.name = name;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(name + " is warming up...");
                Thread.sleep((long) (Math.random() * 2000)); // Random warm-up time
                System.out.println(name + " is ready and waiting at the barrier.");

                barrier.await(); // Waits here until all 4 runners arrive

                // Race begins after the barrier breaks
                System.out.println(name + " is sprinting!");
                Thread.sleep((long) (Math.random() * 3000)); // Simulate race time
                System.out.println(name + " finishes the race!");

            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
