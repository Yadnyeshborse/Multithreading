package com.multitreading.PrimaryExample.Locks.DeadLock;

class Peno {
    public synchronized void writeWithPenAndPaper(Paper paper) {
        System.out.println(Thread.currentThread().getName() + " is using pen " + this + " and trying to use paper " + paper);
        paper.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " finished using pen " + this);
    }
}

class Papero {
    public synchronized void writeWithPaperAndPen(Peno pen) {
        System.out.println(Thread.currentThread().getName() + " is using paper " + this + " and trying to use pen " + pen);
        pen.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " finished using paper " + this);
    }
}

class Task1o implements Runnable {
    private Peno pen;
    private Paper paper;

    public Task1o(Peno pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        pen.writeWithPenAndPaper(paper); // thread1 locks pen and tries to lock paper
    }
}

class Task2o implements Runnable {
    private Peno pen;
    private Paper paper;

    public Task2o(Peno pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        synchronized (pen){
            paper.writeWithPaperAndPen(pen); // thread2 locks paper and tries to lock pen
        }
    }
}


public class Solution{
    public static void main(String[] args) {
        Peno pen = new Peno();
        Paper paper = new Paper();

        Thread thread1 = new Thread(new Task1o(pen, paper), "Thread-1");
        Thread thread2 = new Thread(new Task2o(pen, paper), "Thread-2");

        thread1.start();
        thread2.start();
    }
}
