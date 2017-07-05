package ua.dp.mign.service;

import ua.dp.mign.model.Event;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
