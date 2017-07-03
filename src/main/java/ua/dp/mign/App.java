package ua.dp.mign;

public class App {
    Client client;
    ConsoleEventLogger eventLogger;

    private void logEvent(String message) {
        String formatted = message.replaceAll(client.getId(), client.getFullName());
        eventLogger.logEvent(formatted);
    }

    public static void main(String[] args) {
        App app = new App();
        app.client = new Client("1", "John Smith");
        app.eventLogger = new ConsoleEventLogger();

        app.logEvent("Some event for user 1");
    }
}
