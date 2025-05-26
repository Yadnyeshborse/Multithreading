package com.multitreading.Questions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Even_odd_no_by_two_thread_normal_way_part3 {
    public static void main(String[] args) {
        AtomicInteger number = new AtomicInteger(1);
        int max = 10;

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable printOdd = () -> {
            while (number.get() <= max) {
                if (number.get() % 2 != 0) {
                    System.out.println("Odd: " + number.getAndIncrement());
                }
            }
        };

        Runnable printEven = () -> {
            while (number.get() <= max) {
                if (number.get() % 2 == 0) {
                    System.out.println("Even: " + number.getAndIncrement());
                }
            }
        };


        executor.submit(printEven);
        executor.submit(printOdd);
        executor.shutdown();
    }
}
//Using AtomicInteger with ExecutorService

