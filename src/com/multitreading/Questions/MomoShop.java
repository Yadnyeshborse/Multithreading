package com.multitreading.Questions;

class Momo{
    private String pizza;
    private boolean pizzaReady=false;

    public synchronized void makePizza(String type) throws InterruptedException{
        while (pizzaReady){
            System.out.println("Chef: Waiting for delivery before making next pizza");
            wait();
        }
        System.out.println("Chief start preparing"+type);
        Thread.sleep(500);
        this.pizza=type;
        pizzaReady=true;
        notify();
    }
    public synchronized void deliveredPizza() throws InterruptedException{
        while (!pizzaReady){
            System.out.println("Pizza is going for delivery");
            wait();
        }
        System.out.println("Delivery start= "+pizza);
        Thread.sleep(1000);
        pizzaReady=false;
        notify();
    }
}

public class MomoShop {
    public static void main(String[] args) {
        Momo momo=new Momo();
        new Thread(()->{
            try {
                momo.makePizza("Veggie");
                momo.makePizza("Panner");
                momo.makePizza("Maragrittha");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();
        new Thread(()->{
            try {
                Thread.sleep(1500);
                momo.deliveredPizza();
                momo.deliveredPizza();
                momo.deliveredPizza();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }
}



