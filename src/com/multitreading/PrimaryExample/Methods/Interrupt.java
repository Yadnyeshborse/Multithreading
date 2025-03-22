package com.multitreading.PrimaryExample.Methods;

public class Interrupt extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("Thread is running");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Interrupt interrupt=new Interrupt();
        interrupt.start();
        interrupt.interrupt();
    }


}
