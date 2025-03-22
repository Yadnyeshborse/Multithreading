package com.multitreading.PrimaryExample.Methods;

public class MyThread extends Thread{
    @Override
    public void run() {
        for (int i=0;i<=5;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Pal");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread=new MyThread();
        myThread.start();
        myThread.join();
        System.out.println("Hello");
    }
}
