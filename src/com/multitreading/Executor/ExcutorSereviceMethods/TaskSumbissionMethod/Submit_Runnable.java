package com.multitreading.Executor.ExcutorSereviceMethods.TaskSumbissionMethod;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Submit_Runnable {
    public static void main(String[] args) {

        //Executes a task asynchronously and returns a Future<?>, which can be used to track completion
        //here it is returing future with no value  Future<?>
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable task = () -> System.out.println("Executing task: " + Thread.currentThread().getName());

        Future<?> future = executor.submit(task);

        try {
            future.get();  // Blocks until task is complete
            System.out.println("Task completed!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
