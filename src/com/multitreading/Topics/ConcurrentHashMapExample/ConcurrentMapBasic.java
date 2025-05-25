package com.multitreading.Topics.ConcurrentHashMapExample;


import java.util.concurrent.ConcurrentHashMap;
//Multiple Threads Safely Updating a Shared Map
//other threads can safely update it
public class ConcurrentMapBasic {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                String key = "Thread-" + Thread.currentThread().getId() + "-" + i;
                map.put(key, i);
                System.out.println(Thread.currentThread().getName() + " added " + key);
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Map size: " + map.size());
    }
}

