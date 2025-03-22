package com.multitreading.PrimaryExample.Extend;

public class Test extends Thread{

    @Override
    public void run() {
        for (;;){
            System.out.println(Thread.currentThread().getName());
        }
    }
}
