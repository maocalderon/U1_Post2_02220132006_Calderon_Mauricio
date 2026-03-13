package com.patrones.u2;

public class PushNotifier implements Notifier {
    public String channel() { return "PUSH"; }
    public void send(String r, String m) {
        NotificationLogger.INSTANCE.log(channel(), r, m);
    }
}