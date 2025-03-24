package com.multitreading.PrimaryExample.Locks.Read_write_Lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class SharedResource{
    private int data=0;
    private final ReentrantReadWriteLock lock=new ReentrantReadWriteLock();

    public void readData(String threadName){
        lock.readLock().lock();
        try {
            System.out.println(threadName+" is reading "+data);
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
    }

    public void writeData(int value,String threadName){
        lock.writeLock().lock();
        try {
            System.out.println(threadName+" is trying to read lock "+value);
            Thread.sleep(1000);
            this.data=value;
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }
}
public class SharedResource_read_write_lock {
    public static void main(String[] args) {
        SharedResource sharedResource=new SharedResource();

        Runnable readerTask = () -> sharedResource.readData(Thread.currentThread().getName());
        Runnable writerTask = () -> sharedResource.writeData(42, Thread.currentThread().getName());

        Thread t1 = new Thread(readerTask, "Reader-1");
        Thread t2 = new Thread(readerTask, "Reader-2");
        Thread t3 = new Thread(writerTask, "Writer");

        t1.start();
        t2.start();
        t3.start();
    }

}
