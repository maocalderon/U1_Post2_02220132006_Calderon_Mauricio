package service;
import model.Product;
import observer.*;
import strategy.PricingStrategy;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements OrderSubject { // [cite: 140]
    private List<OrderObserver> observers = new ArrayList<>();
    private String status = "CREATED";

    public void processOrder(Product product, PricingStrategy strategy) {
        double finalPrice = strategy.calculateFinalPrice(product.getBasePrice()); // [cite: 140]
        System.out.println("Procesando: " + product.getDescription());
        System.out.println("Estrategia aplicada: " + strategy.getDescription());
        System.out.println("Total con envío: $" + (finalPrice + product.calculateShipping()));
    }

    public void changeStatus(String orderId, String newStatus) {
        String oldStatus = this.status;
        this.status = newStatus;
        notifyObservers(orderId, oldStatus, newStatus); // [cite: 53, 63]
    }

    @Override
    public void subscribe(OrderObserver observer) { observers.add(observer); } // [cite: 61]

    @Override
    public void unsubscribe(OrderObserver observer) { observers.remove(observer); } // [cite: 62]

    @Override
    public void notifyObservers(String orderId, String oldS, String newS) {
        for (OrderObserver o : observers) {
            o.update(orderId, oldS, newS); // [cite: 64]
        }
    }
}