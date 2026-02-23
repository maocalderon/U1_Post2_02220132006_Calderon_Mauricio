package observer;

public class LogNotifier implements OrderObserver {
    @Override
    public void update(String orderId, String oldStatus, String newStatus) {
        System.out.println("[LOG] 📝 Pedido " + orderId + ": Cambio de " + oldStatus + " a " + newStatus);
    }
}