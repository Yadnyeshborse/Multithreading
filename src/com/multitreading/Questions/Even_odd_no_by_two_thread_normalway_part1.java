package com.multitreading.Questions;

class NumberPrinter {
    private int number = 1;
    private final int max;

    public NumberPrinter(int max) {
        this.max = max;
    }

    public synchronized void printOdd() {
        while (number <= max) {
            if (number % 2 == 1) {
                System.out.println("Odd: " + number);
                number++;
                notify();  // Wake up even thread
            } else {
                try {
                    wait(); // Wait until odd turn
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public synchronized void printEven() {
        while (number <= max) {
            if (number % 2 == 0) {
                System.out.println("Even: " + number);
                number++;
                notify();  // Wake up odd thread
            } else {
                try {
                    wait(); // Wait until even turn
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

public class Even_odd_no_by_two_thread_normalway_part1 {
    public static void main(String[] args) {
        NumberPrinter1 printer = new NumberPrinter1(10); // up to 10

        Thread oddThread = new Thread(() -> printer.printOdd());
        Thread evenThread = new Thread(() -> printer.printEven());

        oddThread.start();
        evenThread.start();
    }
}

