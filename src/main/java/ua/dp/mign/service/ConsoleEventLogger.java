package ua.dp.mign.service;

import org.springframework.stereotype.Component;
import ua.dp.mign.model.Event;

@Component("consoleLogger")
public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
