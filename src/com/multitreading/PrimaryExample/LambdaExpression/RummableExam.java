package com.multitreading.PrimaryExample.LambdaExpression;

public class RummableExam {
    public static void main(String[] args) {
//        Runnable runnable=new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Run");
//            }
//        };
        Runnable runnable=()->{
            System.out.println("hello");
        };
        Thread thread=new Thread(runnable);
        thread.start();
        //another way
        Thread thread1=new Thread(()->System.out.println("Bye"));
        thread1.start();

    }
}
