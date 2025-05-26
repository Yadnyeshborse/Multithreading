package com.multitreading.Questions;


class Number{
    int initial=1;
    public final int max;

    public Number(int max) {
        this.max = max;
    }

    public synchronized void evenNo(){
        while (initial<=max){
            if (initial%2==0){
                System.out.println("Even no="+initial);
                initial++;
                notify();
            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                   // throw new RuntimeException(e);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public synchronized void oddNo(){
        while (initial<=max){
            if (initial%2!=0){
                System.out.println("Odd no="+initial);
                initial++;
                notify();
            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    // throw new RuntimeException(e);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
public class Interview {
    public static void main(String[] args) {
        Number  number=new Number(10);
        Thread evenNo=new Thread(()->number.evenNo());
        Thread oddNp=new Thread(()->number.oddNo());
        evenNo.start();
        oddNp.start();
    }
}
