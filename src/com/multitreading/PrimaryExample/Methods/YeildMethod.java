package com.multitreading.PrimaryExample.Methods;

public class YeildMethod  extends Thread{
    @Override
    public void run() {
        for (int i=0;i<5;i++){
            System.out.println(Thread.currentThread().getName()+"is running");
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        YeildMethod yeildMethod1 =new YeildMethod();
        YeildMethod yeildMethod2 =new YeildMethod();
        yeildMethod1.start();
        yeildMethod2.start();


    }
}
//without yeild
//Thread-1is running
//Thread-1is running
//Thread-1is running
//Thread-1is running
//Thread-1is running
//Thread-0is running
//Thread-0is running
//Thread-0is running
//Thread-0is running
//Thread-0is running

//with yeild
//Thread-0is running
//Thread-1is running
//Thread-0is running
//Thread-0is running
//Thread-1is running
//Thread-0is running
//Thread-0is running
//Thread-1is running
//Thread-1is running
//Thread-1is running


