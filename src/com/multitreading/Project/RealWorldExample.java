package com.multitreading.Project;

import java.util.concurrent.CompletableFuture;

// Main class
public class RealWorldExample {
    public static void main(String[] args) {
        ECommerceService service = new ECommerceService();

        // Simulating an order process
        service.processOrder("user123", "item456")
                .thenAccept(order -> {
                    if (order != null) {
                        System.out.println("Order placed successfully: " + order);
                    } else {
                        System.out.println("Order failed!");
                    }
                });

        // Ensuring async tasks complete before program exits
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Service class handling e-commerce transactions
class ECommerceService {
    public CompletableFuture<Order> processOrder(String userId, String itemId) {
        return getUserProfile(userId)
                .thenCombine(getInventory(itemId), (user, inventory) -> {
                    if (inventory.stock > 0) {
                        return new Order(user, itemId, inventory.price);
                    }
                    throw new RuntimeException("Out of stock");
                })
                .thenCompose(order ->
                        processPayment(order)
                                .thenApply(receipt -> finalizeOrder(order, receipt))
                )
                .exceptionally(ex -> {
                    System.err.println("Order failed: " + ex.getMessage());
                    return null;
                });
    }

    // Helper async methods
    private CompletableFuture<User> getUserProfile(String userId) {
        return CompletableFuture.supplyAsync(() -> new User(userId, "John Doe"));
    }

    private CompletableFuture<Inventory> getInventory(String itemId) {
        return CompletableFuture.supplyAsync(() -> new Inventory(itemId, 5, 100.0));
    }

    private CompletableFuture<Receipt> processPayment(Order order) {
        return CompletableFuture.supplyAsync(() -> new Receipt(order.itemId, order.price, "Paid"));
    }

    private Order finalizeOrder(Order order, Receipt receipt) {
        System.out.println("Payment confirmed for Order: " + order.itemId);
        return order;
    }
}

// Supporting model classes
class User {
    String userId;
    String name;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}

class Inventory {
    String itemId;
    int stock;
    double price;

    public Inventory(String itemId, int stock, double price) {
        this.itemId = itemId;
        this.stock = stock;
        this.price = price;
    }
}

class Order {
    User user;
    String itemId;
    double price;

    public Order(User user, String itemId, double price) {
        this.user = user;
        this.itemId = itemId;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{user=" + user.name + ", itemId=" + itemId + ", price=" + price + "}";
    }
}

class Receipt {
    String itemId;
    double amount;
    String status;

    public Receipt(String itemId, double amount, String status) {
        this.itemId = itemId;
        this.amount = amount;
        this.status = status;
    }
}
