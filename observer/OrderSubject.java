package observer;

public interface OrderSubject {
    void subscribe(OrderObserver observer); // [cite: 61]
    void unsubscribe(OrderObserver observer); // [cite: 62]
    void notifyObservers(String orderId, String oldStatus, String newStatus); // [cite: 63, 64]
}