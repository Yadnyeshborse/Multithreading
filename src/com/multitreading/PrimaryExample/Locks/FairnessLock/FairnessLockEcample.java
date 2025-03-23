    package com.multitreading.PrimaryExample.Locks.FairnessLock;

    import java.util.concurrent.locks.Lock;
    import java.util.concurrent.locks.ReentrantLock;

    public class FairnessLockEcample {
        private Lock lock=new ReentrantLock(true);
        //this true makes difference this maintain consistency

        public void accessResource(){
            lock.lock();
            try {
                    System.out.println(Thread.currentThread().getName()+" acquired the access");
            }catch (Exception e){
                Thread.currentThread().interrupt();
            }finally {
                System.out.println(Thread.currentThread().getName()+" completed the process");
                lock.unlock();
            }
        }
        public static void main(String[] args) {
            FairnessLockEcample example = new FairnessLockEcample();

            Runnable task = new Runnable() {
                @Override
                public void run() {
                    example.accessResource();
                }
            };

            Thread thread1 = new Thread(task, "Thread 1");
            Thread thread2 = new Thread(task, "Thread 2");
            Thread thread3 = new Thread(task, "Thread 3");

            thread1.start();
            thread2.start();
            thread3.start();
        }

    }
