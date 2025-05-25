package com.multitreading.Topics.ConcurrentHashMapExample;


import java.util.concurrent.ConcurrentHashMap;

class Worker implements Runnable {
    private ConcurrentHashMap<String, Integer> map;
    private String key;
    private int value;

    public Worker(ConcurrentHashMap<String, Integer> map, String key, int value) {
        this.map = map;
        this.key = key;
        this.value = value;
    }

    public void run() {
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + " inserted: " + key + " -> " + value);
    }
}


public class ConcurrentHashMapExample1 {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        Thread t1 = new Thread(new Worker(map, "A", 1));
        Thread t2 = new Thread(new Worker(map, "B", 2));
        Thread t3 = new Thread(new Worker(map, "C", 3));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Final map: " + map);
    }
}

