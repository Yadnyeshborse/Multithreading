package com.multitreading.Executor.A;

public class Factorial_by_Synchro {
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();// 1 jan to 1970 to current time milli sec
        for (int i=1;i<10;i++){
            System.out.println(factorial(i));
        }
        //if we got my synchronously that it give 9093 sec to complete let try my multithreading
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
