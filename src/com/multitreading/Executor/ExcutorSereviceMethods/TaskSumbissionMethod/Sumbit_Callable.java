package com.multitreading.Executor.ExcutorSereviceMethods.TaskSumbissionMethod;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Sumbit_Callable {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<Integer> task = () -> {
            Thread.sleep(1000);
            return 42;
        };

        Future<Integer> future = executor.submit(task);
        //here it is returing future with no value  Future<?>

        System.out.println("Result: " + future.get());  // Blocks until task completes

        executor.shutdown();

        //Executes a Callable asynchronously and returns a Future<T> with the result.
    }
}
