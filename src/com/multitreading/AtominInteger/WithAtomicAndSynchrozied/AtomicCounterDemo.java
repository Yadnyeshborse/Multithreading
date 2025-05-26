package com.multitreading.AtominInteger.WithAtomicAndSynchrozied;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounterDemo {
    private static AtomicInteger counter=new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException{
        Runnable task=()->{
            for (int i=0;i<1000;i++){
                counter.getAndIncrement();
            }
        };
        Thread thread=new Thread(task);
        Thread thread1=new Thread(task);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println("Final counter="+counter);
    }
}
