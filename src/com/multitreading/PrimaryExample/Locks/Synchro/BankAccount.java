package com.multitreading.PrimaryExample.Locks.Synchro;

public class BankAccount {
    private int balance=100;

    public synchronized void withDrawal(int amount){
        System.out.println(Thread.currentThread().getName()+" is trying to withdrawal money ");
        if (balance>amount){
            System.out.println(Thread.currentThread().getName()+" is trying to withdraw the money  "+  " amount is"+amount);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            balance-=amount;
            System.out.println(Thread.currentThread().getName()+" is withdraw the money balance now is "+balance);
        }else {
            System.out.println(Thread.currentThread().getName()+ "No sufficient balanced");
        }

    }
}
