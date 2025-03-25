package com.multithreading.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Example2 {
    public static void main(String[] args) {
        int numberOfSubsystems = 4;
        CyclicBarrier barrier = new CyclicBarrier(numberOfSubsystems, () ->
                System.out.println("\nAll subsystems are up and running. System startup complete.")
        );

        new Thread(new Subsystem("Web Server", 2000, barrier)).start();
        new Thread(new Subsystem("Database", 4000, barrier)).start();
        new Thread(new Subsystem("Cache", 3000, barrier)).start();
        new Thread(new Subsystem("Messaging Service", 3500, barrier)).start();
    }
}

class Subsystem implements Runnable {
    private String name;
    private int initializationTime;
    private CyclicBarrier barrier;

    public Subsystem(String name, int initializationTime, CyclicBarrier barrier) {
        this.name = name;
        this.initializationTime = initializationTime;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " initialization started.");
            Thread.sleep(initializationTime); // Simulate initialization
            System.out.println(name + " initialization complete.");
            barrier.await(); // Wait for all subsystems
        } catch (InterruptedException | BrokenBarrierException e) {
            System.err.println(name + " failed to initialize!");
            Thread.currentThread().interrupt();
        }
    }
}