package observer;

public interface OrderObserver {
    // Método que recibirán los observadores con la info del cambio [cite: 58]
    void update(String orderId, String oldStatus, String newStatus);
}