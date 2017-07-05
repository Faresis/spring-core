package ua.dp.mign;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.dp.mign.model.Client;
import ua.dp.mign.service.EventLogger;

public class App {
    private final Client client;
    private final EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    private void logEvent(String message) {
        String formatted = message.replaceAll(client.getId(), client.getFullName());
        eventLogger.logEvent(formatted);
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = context.getBean(App.class);
        app.logEvent("Some event for user 1");
    }
}
