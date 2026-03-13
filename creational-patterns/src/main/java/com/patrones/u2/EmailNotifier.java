package com.patrones.u2;
public class EmailNotifier implements Notifier {
    public String channel() { return "EMAIL"; }
    public void send(String r, String m) {
        NotificationLogger.INSTANCE.log(channel(), r, m);
    }
}