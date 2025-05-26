package com.multitreading.AtominInteger;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {
    // Instance variable (removed 'public' modifier - not allowed here)
    private AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        counter.incrementAndGet();
    }

    public int getValue() {
        return counter.get();
    }

    public boolean compareAndSet(int expect, int update) {
        return counter.compareAndSet(expect, update);
    }

    public static void main(String[] args) {
        // Create instance of AtomicExample
        AtomicExample example = new AtomicExample();

        // Demonstrate usage
        example.increment();
        System.out.println("Current value: " + example.getValue());

        boolean success = example.compareAndSet(1, 10);
        System.out.println("CAS operation successful: " + success);
        System.out.println("New value: " + example.getValue());
    }
}