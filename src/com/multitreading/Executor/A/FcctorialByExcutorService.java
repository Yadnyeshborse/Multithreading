package com.multitreading.Executor.A;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class FcctorialByExcutorService {
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();// 1 jan to 1970 to current time milli sec
        ExecutorService excutor= Executors.newFixedThreadPool(9);
        //we can specify no of thread we used above 3 4 5 6
        //as we increased no of thread time will get reduced
        for (int i=1;i<10;i++){
            int finalI=i;
            excutor.submit(()->{
                long result=factorial(finalI);
                System.out.println(result);
            });
        }
        excutor.shutdown();
        //if we dont use excutorAwaitTermination this will below line be print
        //System.out.println("Total time :"+(System.currentTimeMillis()-startTime));

        try {
            //if we used Millsecond than it will show waiting
            while (!excutor.awaitTermination(100, TimeUnit.SECONDS)){
                System.out.println("Waiting......");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Total time :"+(System.currentTimeMillis()-startTime));
    }

    private static long factorial(int n) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long result=1;
        for (int j=1;j<=n;j++){
            result*=j;
        }
        return  result;
    }
}
