package com.multitreading.PrimaryExample.Locks.Exeplicit;

public class Test {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();

        // Create a task for withdrawal
        Runnable task = () -> bankAccount.withdraw(20);

        // Create and start threads
        Thread t1 = new Thread(task, "Thread1");
        Thread t2 = new Thread(task, "Thread2");
        t1.start();
        t2.start();

        // Wait for threads to complete
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted.");
            Thread.currentThread().interrupt();
        }


    }
}