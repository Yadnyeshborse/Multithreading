package com.multitreading.Questions;

import java.util.concurrent.Semaphore;

class NumberPrinter4 {
    private final int max;
    private int number = 1;
    private final Semaphore evenSemaphore = new Semaphore(0);
    private final Semaphore oddSemaphore = new Semaphore(1);  // Odd thread gets the first turn

    public NumberPrinter4(int max) {
        this.max = max;
    }

    public void printOdd() {
        while (number <= max) {
            try {
                oddSemaphore.acquire(); // Wait until it's odd thread's turn
                if (number <= max) {
                    System.out.println("Odd: " + number++);
                }
                evenSemaphore.release(); // Allow even thread to proceed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void printEven() {
        while (number <= max) {
            try {
                evenSemaphore.acquire(); // Wait until it's even thread's turn
                if (number <= max) {
                    System.out.println("Even: " + number++);
                }
                oddSemaphore.release(); // Allow odd thread to proceed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
//Using Semaphore (Simple & Thread-safe)
public class Even_odd_no_by_two_thread_normalway_part4 {
    public static void main(String[] args) {
        NumberPrinter4 printer = new NumberPrinter4(10);
        new Thread(printer::printOdd).start();
        new Thread(printer::printEven).start();
    }
}

