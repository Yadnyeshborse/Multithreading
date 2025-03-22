package com.multitreading.PrimaryExample.Synchronization;

public class Counter {
    private  int count=0;
    //here we make it synchronized by synchronized, it will help access thread one at time
    //one thread at a time
    //multiple thread will not access it at same time
    public synchronized void increment(){
        count++;
    }
    public int getCount(){
        return count;
    }
}
