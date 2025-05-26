package com.multitreading.AtominInteger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class OddAndEvenNO {
    public static void main(String[] args) {
        AtomicInteger integer=new AtomicInteger(1);
        ExecutorService service= Executors.newFixedThreadPool(2);
        int max=10;

        Runnable evenNo=()->{
            while (true) {
                int value = integer.get();
                if (value > max) break;
                if (value % 2 == 0) {
                    System.out.println("Even no = " + value);
                    integer.incrementAndGet();
                }
            }
        };

        Runnable printOdd=()->{
            while (true) {
                int value = integer.get();
                if (value > max) break;
                if (value % 2 != 0) {
                    System.out.println("Odd no = " + value);
                    integer.incrementAndGet();
                }
            }
        };
        service.submit(evenNo);
        service.submit(printOdd);
        service.shutdown();
    }
}
