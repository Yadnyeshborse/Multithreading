package com.multitreading.Executor.SheduledExcutorServicePack;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SheduleExcutorServiveMethod {
    public static void main(String[] args) {
        ScheduledExecutorService serviveMethod= Executors.newScheduledThreadPool(1);
        serviveMethod.schedule(
                ()->System.out.println("Task executed after 5 sec delay"),5, TimeUnit.SECONDS
        );
        serviveMethod.shutdown();
    }
}
