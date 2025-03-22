package com.multitreading.PrimaryExample.LifeCycleOfThread;

public class MyThread  extends Thread{
    @Override
    public void run() {
        System.out.println("RUNNINGG");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread thread=new MyThread();
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        Thread.sleep(100);
        System.out.println(thread.getState());
        thread.join();
        System.out.println(thread.getState());

    }
}
