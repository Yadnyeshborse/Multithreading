package com.multitreading.PrimaryExample.Locks.Exeplicit;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance = 100; // Make balance private
    private final Lock lock = new ReentrantLock(); // Use final for lock

    public void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " is trying to access the amount.");

        try {
            // Try to acquire the lock with a timeout
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println(Thread.currentThread().getName() + " has acquired the lock.");

                    if (balance >= amount) {
                        System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount + ".");
                        Thread.sleep(3000); // Simulate processing time
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " has withdrawn " + amount + ". Balance is now " + balance + ".");
                    } else {
                        System.out.println(Thread.currentThread().getName() + " cannot withdraw. Insufficient balance.");
                    }
                } finally {
                    // Always release the lock in a finally block
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + " has released the lock.");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " could not acquire the lock. Timeout occurred.");
            }
        } catch (InterruptedException e) {
            // Handle interruption properly
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
            Thread.currentThread().interrupt(); // Restore the interrupted status
        }
    }
}