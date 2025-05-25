package com.multitreading.Project;

import java.util.Comparator;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;

public class RestaurantOrderSystem {
    private static final int NUM_WAITERS = 3;
    private static final int NUM_CHEFS = 2;
    private static final int ORDERS_PER_WAITER = 5;
    private static final AtomicInteger orderIdGenerator = new AtomicInteger(1);
    private static final Random random = new Random();

    // Shared data structures
    private static final BlockingQueue<Order> orderQueue = new PriorityBlockingQueue<>(20, Comparator.reverseOrder());
    private static final ConcurrentMap<Integer, Order> orderTracking = new ConcurrentHashMap<>();
    private static final Order POISON_PILL = new Order(-1, "SHUTDOWN", Integer.MAX_VALUE);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch waitersLatch = new CountDownLatch(NUM_WAITERS);
        ExecutorService waiterPool = Executors.newFixedThreadPool(NUM_WAITERS);
        ExecutorService chefPool = Executors.newFixedThreadPool(NUM_CHEFS);

        // Start waiters
        for (int i = 1; i <= NUM_WAITERS; i++) {
            waiterPool.execute(new Waiter("Waiter-" + i, waitersLatch));
        }

        // Start chefs
        for (int i = 1; i <= NUM_CHEFS; i++) {
            chefPool.execute(new Chef("Chef-" + i));
        }

        // Wait for all waiters to finish
        waitersLatch.await();
        waiterPool.shutdown();

        // Send shutdown signal to chefs (one per chef)
        for (int i = 0; i < NUM_CHEFS; i++) {
            orderQueue.put(POISON_PILL);
        }

        chefPool.shutdown();
        chefPool.awaitTermination(5, TimeUnit.SECONDS);

        // Print final status
        printOrderTrackingReport();
    }

    static class Waiter implements Runnable {
        private final String name;
        private final CountDownLatch latch;

        Waiter(String name, CountDownLatch latch) {
            this.name = name;
            this.latch = latch;
        }

        public void run() {
            try {
                for (int i = 0; i < ORDERS_PER_WAITER; i++) {
                    int priority = random.nextInt(3); // 0 = Highest, 1 = Medium, 2 = Low
                    Order order = new Order(orderIdGenerator.getAndIncrement(), "Customer-" + (i + 1), priority);
                    order.setStatus("CREATED");
                    orderTracking.put(order.getId(), order);
                    orderQueue.put(order);

                    System.out.printf("[%s] Placed %s%n", name, order);
                    Thread.sleep(random.nextInt(400));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown();
                System.out.printf("[%s] Finished shift%n", name);
            }
        }
    }

    static class Chef implements Runnable {
        private final String name;

        Chef(String name) {
            this.name = name;
        }

        public void run() {
            try {
                while (true) {
                    Order order = orderQueue.take();
                    if (order == POISON_PILL) {
                        System.out.printf("[%s] Shutting down%n", name);
                        return;
                    }
                    processOrder(order);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void processOrder(Order order) {
            try {
                order.setStatus("PROCESSING");
                System.out.printf("[%s] Processing %s%n", name, order);

                if (random.nextDouble() < 0.2) { // 20% failure rate
                    throw new RuntimeException("Cooking failure!");
                }

                Thread.sleep(random.nextInt(2000)); // Cooking time
                order.setStatus("COMPLETED");
                System.out.printf("[%s] Completed %s%n", name, order);
            } catch (Exception e) {
                order.setStatus("FAILED");
                System.out.printf("[%s] Failed to process %s: %s%n",
                        name, order, e.getMessage());
            }
        }
    }

    static class Order implements Comparable<Order> {
        private final int id;
        private final String customer;
        private final int priority;
        private volatile String status;

        public Order(int id, String customer, int priority) {
            this.id = id;
            this.customer = customer;
            this.priority = priority;
        }

        public int getId() {
            return id;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int compareTo(Order other) {
            return Integer.compare(other.priority, this.priority); // Reverse order (high priority first)
        }

        public String toString() {
            return String.format("Order#%04d (Priority %d) for %s [%s]", id, priority, customer, status);
        }
    }

    private static void printOrderTrackingReport() {
        System.out.println("\n=== FINAL ORDER STATUS REPORT ===");
        orderTracking.values().stream()
                .sorted(Comparator.comparingInt(Order::getId))
                .forEach(order -> System.out.printf(
                        "Order#%04d | Priority: %d | Status: %-12s | Customer: %s%n",
                        order.getId(), order.priority, order.status, order.customer
                ));
    }
}


//
//Problem Statement
//Create a multithreaded restaurant system that:
//
//Handles multiple waiters taking orders concurrently
//
//Processes orders based on priority (VIP > Regular)
//
//Tracks order status in real-time
//
//Handles cooking failures gracefully
//
//Provides a final order status report
//
//Shuts down cleanly after all orders are processed
//
//Key Challenges:
//
//Thread-safe order prioritization
//
//Coordinating multiple producers/consumers
//
//Preventing race conditions in status updates
//
//Graceful system termination


//Code Description
//1. Core Components
//Component	Purpose
//Order Class	Represents an order with ID, customer, priority, and status
//Waiter Threads	Multiple producers adding orders to queue
//Chef Threads	Multiple consumers processing orders from queue
//PriorityBlockingQueue	Thread-safe priority queue for orders
//ConcurrentHashMap	Thread-safe order tracking system
//POISON_PILL	Special order to signal system shutdown
//2. Key Features
//Feature	Implementation
//Priority Handling	Order implements Comparable for priority-based queue ordering
//Error Handling	20% random failure rate with status updates
//Order Tracking	Atomic order IDs + ConcurrentHashMap for real-time status
//Graceful Shutdown	CountDownLatch for waiters + poison pill for chefs
//Thread Management	ExecutorService pool for chefs + manual thread creation for waiters

