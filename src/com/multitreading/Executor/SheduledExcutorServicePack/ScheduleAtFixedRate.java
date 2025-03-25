package com.multitreading.Executor.SheduledExcutorServicePack;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleAtFixedRate {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        // Schedules a task to run every 5 seconds with an initial delay of 5 seconds
        executorService.scheduleAtFixedRate(
                () -> System.out.println("Task scheduled at a fixed 5 sec delay"),
                5, 5, TimeUnit.SECONDS
        );

        // Schedules a task to shut down the executor after 20 seconds
        executorService.schedule(() -> {
            System.out.println("Initiating shutdown.....");
            executorService.shutdown();
        }, 20, TimeUnit.SECONDS);
    }
}

