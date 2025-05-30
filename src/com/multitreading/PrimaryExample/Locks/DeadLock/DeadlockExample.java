package com.multitreading.PrimaryExample.Locks.DeadLock;

class Pen {
    public synchronized void writeWithPenAndPaper(Paper paper) {
        System.out.println(Thread.currentThread().getName() + " is using pen " + this + " and trying to use paper " + paper);
        paper.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " finished using pen " + this);
    }
}

class Paper {
    public synchronized void writeWithPaperAndPen(Peno pen) {
        System.out.println(Thread.currentThread().getName() + " is using paper " + this + " and trying to use pen " + pen);
        pen.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " finished using paper " + this);
    }
}

class Task1 implements Runnable {
    private Peno pen;
    private Paper paper;

    public Task1(Peno pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        pen.writeWithPenAndPaper(paper); // thread1 locks pen and tries to lock paper
    }
}

class Task2 implements Runnable {
    private Peno pen;
    private Paper paper;

    public Task2(Peno pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {

            paper.writeWithPaperAndPen(pen); // thread2 locks paper and tries to lock pen

    }
}


//Here you will see task one trying to access pen
//and task2 trying to access papaer so it is locking scene
//no one can access any other things they are locking each other
//this is deadlock
//to avoid this we put one object in synchoried block so that if paper is accesss it need to have access to pen also only at that time it can get access


public class DeadlockExample {
    public static void main(String[] args) {
        Peno pen = new Peno();
        Paper paper = new Paper();

        Thread thread1 = new Thread(new Task1o(pen, paper), "Thread-1");
        Thread thread2 = new Thread(new Task2o(pen, paper), "Thread-2");

        thread1.start();
        thread2.start();
    }
}