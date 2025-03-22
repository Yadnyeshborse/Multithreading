package com.multitreading.PrimaryExample.Synchronization;

public class Testo extends Thread {
    private Counter counter;
    public Testo(Counter counter){
        this.counter=counter;
    }

    @Override
    public void run() {
        for (int i=0;i<1000;i++){
            counter.increment();
        }
    }
}
