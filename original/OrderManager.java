package original;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<String[]> orders = new ArrayList<>();

    public void createOrder(String customer, String product, double price, int quantity) {
        double total = price * quantity;
        if (total > 1000) total *= 0.9; 
        if (total > 5000) total *= 0.85;
        
        String orderId = "ORD-" + System.currentTimeMillis();
        orders.add(new String[] {orderId, customer, product, String.valueOf(total), "PENDING"});
        System.out.println("Orden creada: " + orderId);

        try {
            java.io.FileWriter fw = new java.io.FileWriter("orders.txt", true);
            fw.write(orderId + "," + customer + "," + total + "\n");
            fw.close();
        } catch (Exception e) { e.printStackTrace(); }

        System.out.println("EMAIL a " + customer + ": Su pedido " + orderId + " ha sido creado.");
    }
}