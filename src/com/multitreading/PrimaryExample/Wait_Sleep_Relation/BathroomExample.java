package com.multitreading.PrimaryExample.Wait_Sleep_Relation;

import java.util.concurrent.CountDownLatch;

class Bathroom {
    private boolean occupied = false;

    public synchronized void useBathroom(String name) throws InterruptedException {
        if (occupied) {
            System.out.println(name + " finds bathroom occupied - waits");
        }

        while (occupied) {
            wait(); // wait until bathroom is free
        }

        occupied = true;
        System.out.println(name + " enters bathroom");
        Thread.sleep(3000); // simulate time spent in bathroom
        System.out.println(name + " exits bathroom");
        occupied = false;
        notifyAll(); // notify other waiting threads
    }
}

public class BathroomExample {
    public static void main(String[] args) {
        Bathroom bathroom = new Bathroom();
        String[] people = {"Alice", "Bob", "Charlie", "Diana", "Eve"};

        CountDownLatch latch = new CountDownLatch(1); // to release all threads at once

        for (String person : people) {
            new Thread(() -> {
                try {
                    latch.await(); // all threads wait here
                    bathroom.useBathroom(person);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }

        try {
            Thread.sleep(1000); // give threads time to reach latch.await()
            System.out.println("🚦 All people released to bathroom at the same time...\n");
            latch.countDown(); // let all threads run
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
