package ua.dp.mign.service;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(String message) {
        System.out.println(message);
    }
}
