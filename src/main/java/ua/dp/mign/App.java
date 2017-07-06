package ua.dp.mign;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.dp.mign.model.Client;
import ua.dp.mign.model.Event;
import ua.dp.mign.service.EventLogger;

public abstract class App {
    private final Client client;
    private final EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public abstract Event getEvent();

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = context.getBean(App.class);

        for (int i = 0; i < 10; ++i) {
            Event event = app.getEvent();
            event.setMessage("Some event for user " + i);
            app.logEvent(event);
            Thread.sleep(1000);
        }

        context.close();
    }

    private void logEvent(Event event) {
        String formatted = event.getMessage().replaceAll(client.getId(), client.getFullName());
        event.setMessage(formatted);
        eventLogger.logEvent(event);
    }
}
