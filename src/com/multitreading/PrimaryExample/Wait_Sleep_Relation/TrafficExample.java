package com.multitreading.PrimaryExample.Wait_Sleep_Relation;

class Intersection {
    private boolean greenLight = false;

    public synchronized void carArrives(int id) throws InterruptedException {
        while(!greenLight) {
            System.out.println("Car " + id + " waiting at red light");
            wait();
        }
        System.out.println("Car " + id + " crossing intersection");
    }

    public synchronized void switchLight() {
        greenLight = !greenLight;
        if(greenLight) {
            System.out.println("\nLight turned GREEN - notifying all cars\n");
            notifyAll(); // Notify all waiting cars
        } else {
            System.out.println("\nLight turned RED\n");
        }
    }
}

public class TrafficExample {
    public static void main(String[] args) {
        Intersection intersection = new Intersection();

        // Traffic light controller
        new Thread(() -> {
            try {
                while(true) {
                    Thread.sleep(3000); // Light changes every 3 seconds
                    intersection.switchLight();
                }
            } catch (InterruptedException e) {}
        }).start();

        // Cars arriving at random intervals
        for(int i=1; i<=5; i++) {
            final int id = i;
            new Thread(() -> {
                try {
                    Thread.sleep((long)(Math.random() * 5000));
                    intersection.carArrives(id);
                } catch (InterruptedException e) {}
            }).start();
        }
    }
}
