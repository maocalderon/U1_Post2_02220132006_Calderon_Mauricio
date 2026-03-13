package com.patrones.u2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton (variante enum): gestor de logs de notificaciones.
 */
public enum NotificationLogger {
    INSTANCE; // Única instancia en toda la aplicación

    private final List<String> entries = new ArrayList<>();
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void log(String channel, String recipient, String message) {
        String entry = String.format("[%s] [%s] -> %s: %s",
            LocalDateTime.now().format(FMT), channel, recipient, message);
        entries.add(entry);
        System.out.println(entry);
    }

    public void printAll() {
        System.out.println("\n=== Historial de Notificaciones ===");
        entries.forEach(System.out::println);
        System.out.println("Total: " + entries.size() + " notificaciones");
    }
}