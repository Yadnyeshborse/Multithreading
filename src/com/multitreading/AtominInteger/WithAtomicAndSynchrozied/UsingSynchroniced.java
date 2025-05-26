package com.multitreading.AtominInteger.WithAtomicAndSynchrozied;

public class UsingSynchroniced {
    public static void main(String[] args) throws InterruptedException {
        Runnable task=()->{
            for (int i=0;i<1000;i++){
                increment();
            }
        };
        Thread thread=new Thread(task);
        Thread thread1=new Thread(task);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();

        System.out.println("Final COunter="+count);
    }

    public static int count=0;
    private synchronized static void increment() {
        count++;
    }
}
