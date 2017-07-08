package ua.dp.mign;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.dp.mign.model.Client;
import ua.dp.mign.model.Event;
import ua.dp.mign.model.EventType;
import ua.dp.mign.service.EventLogger;

import java.util.Map;

public abstract class App {
    private final Client client;
    private final EventLogger defaultEventLogger;
    private final Map<EventType, EventLogger> typedEventLoggers;

    public App(Client client, EventLogger defaultEventLogger, Map<EventType, EventLogger> typedEventLoggers) {
        this.client = client;
        this.defaultEventLogger = defaultEventLogger;
        this.typedEventLoggers = ImmutableMap.copyOf(typedEventLoggers);
    }

    public abstract Event getEvent();

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = context.getBean(App.class);

        for (int i = 0; i < 10; ++i) {
            Event event = app.getEvent();
            event.setMessage("Some event for user " + i);

            app.logEvent(getEventType(i), event);
            Thread.sleep(1000);
        }

        context.close();
    }

    private void logEvent(EventType eventType, Event event) {
        String formatted = client.getGreeting() + event.getMessage().replaceAll(client.getId(), client.getFullName());
        event.setMessage(formatted);

        EventLogger logger = MoreObjects.firstNonNull(typedEventLoggers.get(eventType), defaultEventLogger);
        logger.logEvent(event);
    }

    private static EventType getEventType(int ceed) {
        return ceed % (1 + EventType.values().length) == 0 ? null : EventType.values()[ceed % EventType.values().length];
    }
}
