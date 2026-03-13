package com.patrones.u2;

public class SmsNotifier implements Notifier {
    public String channel() { return "SMS"; }
    public void send(String r, String m) {
        NotificationLogger.INSTANCE.log(channel(), r, m);
    }
}