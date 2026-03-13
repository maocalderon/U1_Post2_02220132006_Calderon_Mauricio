package com.patrones.u2;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Demo: Singleton + Factory Method ===\n");

        // 1. Demostración Singleton: Misma instancia desde distintos contextos
        NotificationLogger logger1 = NotificationLogger.INSTANCE;
        NotificationLogger logger2 = NotificationLogger.INSTANCE;
        System.out.println("Misma instancia: " + (logger1 == logger2));

        // 2. Demostración Factory Method: Crear notificadores por tipo
        // El código cliente no conoce las clases concretas (EmailNotifier, etc.)
        Notifier email = NotifierFactory.create("email");
        Notifier sms = NotifierFactory.create("sms");
        Notifier push = NotifierFactory.create("push");

        // 3. Enviar notificaciones: El Singleton registra todas automáticamente
        email.send("cliente@mail.com", "Su pedido #1001 fue confirmado");
        sms.send("+57-300-0000001", "Pedido #1001 confirmado");
        push.send("device-token-abc123", "Nuevo pedido listo para enviar");

        // 4. Registrar un tipo nuevo en tiempo de ejecución (Demostración OCP)
        // Esto permite agregar canales (como Slack) sin modificar la Factory ni recompilar
        NotifierFactory.register("slack", () -> new Notifier() {
            @Override
            public String channel() { return "SLACK"; }

            @Override
            public void send(String r, String m) {
                NotificationLogger.INSTANCE.log(channel(), r, m);
            }
        });

        // Probar el nuevo canal registrado
        NotifierFactory.create("slack").send("#pedidos", "Pedido #1001 procesado");

        // 5. Imprimir historial: El Singleton tiene TODOS los registros
        NotificationLogger.INSTANCE.printAll();
    }
}