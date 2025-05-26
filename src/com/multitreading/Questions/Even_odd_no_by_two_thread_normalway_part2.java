package com.multitreading.Questions;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class NumberPrinter1 {
    private final int max;
    private int number = 1;
    private final Lock lock = new ReentrantLock();
    private final Condition evenCondition = lock.newCondition();
    private final Condition oddCondition = lock.newCondition();

    public NumberPrinter1(int max) {
        this.max = max;
    }

    public void printOdd() {
        while (number <= max) {
            lock.lock();
            try {
                while (number % 2 == 0) {
                    oddCondition.await();  // Wait if not odd
                }
                if (number <= max) {
                    System.out.println("Odd: " + number++);
                    evenCondition.signal();  // Signal even thread
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printEven() {
        while (number <= max) {
            lock.lock();
            try {
                while (number % 2 == 1) {
                    evenCondition.await();  // Wait if not even
                }
                if (number <= max) {
                    System.out.println("Even: " + number++);
                    oddCondition.signal();  // Signal odd thread
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}
// Using ReentrantLock and Condition
public class Even_odd_no_by_two_thread_normalway_part2 {
    public static void main(String[] args) {
        NumberPrinter1 printer = new NumberPrinter1(10);
        Thread t1 = new Thread(printer::printOdd);
        Thread t2 = new Thread(printer::printEven);
        t1.start();
        t2.start();
    }
}

