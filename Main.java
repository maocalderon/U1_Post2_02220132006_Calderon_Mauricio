import factory.ProductFactory;
import model.Product;
import service.OrderService;
import strategy.*;
import observer.*;

public class Main {
    public static void main(String[] args) {
        // 1. Inicializar el servicio (El Sujeto del Observer)
        OrderService service = new OrderService();

        // 2. Suscribir a los observadores (Punto c de la guía)
        service.subscribe(new EmailNotifier());
        service.subscribe(new SMSNotifier());
        service.subscribe(new LogNotifier());

        System.out.println("---SISTEMA DE VENTAS - MAURICIO CALDERON ---");

        // 3. Creacion de productos usando Factory (Punto a de la guía)
        Product laptop = ProductFactory.createProduct("ELECTRONICS", "Laptop Gamer UDES", 3500000);
        Product camisa = ProductFactory.createProduct("CLOTHING", "Camiseta Polo", 85000);
        Product snack = ProductFactory.createProduct("FOOD", "Barra de Cereal", 3500);

        // 4. Calculo de precios con diferentes estrategias (Punto b de la guía)
        System.out.println("\n--- Procesando Pedido 1 ---");
        service.processOrder(laptop, new BlackFridayPricing()); // 30% desc

        System.out.println("\n--- Procesando Pedido 2 ---");
        service.processOrder(camisa, new MemberPricing()); // 10% desc

        System.out.println("\n--- Procesando Pedido 3 ---");
        service.processOrder(snack, new RegularPricing()); // Sin desc

        // 5. Simulacion de cambio de estado (Punto c de la guia)
        System.out.println("\n--- Actualización de Estados (Notificaciones) ---");
        service.changeStatus("ORD-001", "PROCESSING");
        service.changeStatus("ORD-001", "SHIPPED");
        service.changeStatus("ORD-001", "DELIVERED");

        System.out.println("\n--- PRUEBA FINALIZADA CON ÉXITO ---");
    }
}