package com.multitreading.PrimaryExample.Methods;

public class PriorityThread extends Thread {

    @Override
    public void run() {
        for (int i=0;i<5;i++){
            String a="";
            for (int j=0;j<5;j++){
                a+="a";
            }
            System.out.println(Thread.currentThread().getName()+"-Priority- "+Thread.currentThread().getPriority()+"-count-"+i);
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException{

        PriorityThread l=new PriorityThread();
        PriorityThread m=new PriorityThread();
        PriorityThread n=new PriorityThread();
        l.setPriority(Thread.MAX_PRIORITY);
        m.setPriority(Thread.NORM_PRIORITY);
        m.setPriority(Thread.MIN_PRIORITY);
        l.start();
        m.start();
        n.start();

        //even we set prioprty its order is not that much maintain see result
        //that is not such strict rule
    }

}
