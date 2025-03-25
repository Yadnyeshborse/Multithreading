package com.multitreading.Executor.ExcutorSereviceMethods.TaskSumbissionMethod;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InvokeAny {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        //Executes multiple tasks and returns the result of the first completed task.
        List<Callable<String>> tasks = List.of(
                () -> { Thread.sleep(3000); return "Task 1"; },
                () -> { Thread.sleep(1000); return "Task 2"; },
                () -> { Thread.sleep(2000); return "Task 3"; }
        );

        String result = executor.invokeAny(tasks);
        System.out.println("First completed task result: " + result);

        executor.shutdown();
    }
}
