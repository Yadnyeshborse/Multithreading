package com.multitreading.PrimaryExample.Extend;

public class Hello {
    public static void main(String[] args) {
        Test test=new Test();
        test.start();
        for (;;){
            System.out.println(Thread.currentThread().getName());
        }
        //Here two thread running at same time
       // System.out.println("Thread name="+Thread.currentThread().getName());
    }
}
