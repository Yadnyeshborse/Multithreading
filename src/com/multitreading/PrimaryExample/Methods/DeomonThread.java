package com.multitreading.PrimaryExample.Methods;

public class DeomonThread extends Thread {
    @Override
    public void run() {
        while (true){
            System.out.println("Hello world");
        }
    }

    public static void main(String[] args) {
        DeomonThread thread=new DeomonThread();
        thread.setDaemon(true);
        thread.start();
        System.out.println("Main");
    }


}
