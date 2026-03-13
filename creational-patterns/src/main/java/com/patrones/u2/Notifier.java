package com.patrones.u2;
public interface Notifier {
    void send(String recipient, String message); // Método para enviar
    String channel(); // Método para identificar el canal (SMS, EMAIL, etc)
}