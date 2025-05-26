package com.multitreading.PrimaryExample.Wait_Sleep_Relation;

class PizzaShop {
    private String pizza;
    private boolean pizzaReady = false;

    public synchronized void makePizza(String type) throws InterruptedException {
        while(pizzaReady) {
            System.out.println("Chef: Waiting for delivery before making next pizza");
            wait(); // Waits until pizza is delivered
        }
        System.out.println("Chef: Making " + type + " pizza");
        Thread.sleep(2000); // Time to make pizza
        this.pizza = type;
        pizzaReady = true;
        notify(); // Notifies delivery guy
    }

    public synchronized void deliverPizza() throws InterruptedException {
        while(!pizzaReady) {
            System.out.println("Delivery: Waiting for pizza to be ready");
            wait(); // Waits until pizza is made
        }
        System.out.println("Delivery: Delivering " + pizza);
        Thread.sleep(1000); // Delivery time
        pizzaReady = false;
        notify(); // Notifies chef
    }
}

public class PizzaExample {
    public static void main(String[] args) {
        PizzaShop shop = new PizzaShop();

        // Chef thread
        new Thread(() -> {
            try {
                shop.makePizza("Pepperoni");
                shop.makePizza("Margherita");
                shop.makePizza("Veggie");
            } catch (InterruptedException e) {}
        }).start();

        // Delivery thread
        new Thread(() -> {
            try {
                shop.deliverPizza();
                shop.deliverPizza();
                shop.deliverPizza();
            } catch (InterruptedException e) {}
        }).start();
    }
}