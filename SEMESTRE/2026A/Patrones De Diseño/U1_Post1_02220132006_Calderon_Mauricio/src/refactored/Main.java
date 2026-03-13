package refactored;

public class Main {
    public static void main(String[] args) {
        // 1. Configuramos las herramientas (Inyección de Dependencias)
        OrderRepository repo = new FileOrderRepository();
        NotificationService notifier = new EmailNotificationService();
        
        // 2. Probamos con Descuento Estándar
        OrderService servicioNormal = new OrderService(repo, notifier, new StandardDiscount());
        servicioNormal.createOrder("Mauricio", "Laptop", 2500, 1);

        // 3. Probamos con Descuento VIP (Nueva funcionalidad OCP)
        OrderService servicioVIP = new OrderService(repo, notifier, new VIPDiscount());
        servicioVIP.createOrder("Andrés", "Celular", 1000, 1);

        System.out.println("\n--- Proceso Finalizado con Éxito ---");
        System.out.println("Total de pedidos guardados: " + repo.findAll().size());
    }
}