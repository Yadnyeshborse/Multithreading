package com.multitreading.PrimaryExample.Implement;

public class Main {
    public static void main(String[] args) {
        Test test=new Test();
        Thread thread=new Thread(test);
        thread.start();

        for (; ;){
            System.out.println("hello");

        }
    }
}
