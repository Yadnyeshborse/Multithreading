package com.multitreading.PrimaryExample.Locks.Synchro;

public class Test {
    public static void main(String[] args) {
        BankAccount bankAccount=new BankAccount();
        Runnable task=new Runnable() {
            @Override
            public void run() {
                bankAccount.withDrawal(20);
            }
        };
        Thread t1=new Thread(task,"Thread1");
        Thread t2=new Thread(task,"Thread2");
        t1.start();
        t2.start();
    }
}
