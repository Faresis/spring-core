package ua.dp.mign;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(String message) {
        System.out.println(message);
    }
}
