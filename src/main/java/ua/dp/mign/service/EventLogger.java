package ua.dp.mign.service;

import ua.dp.mign.model.Event;

public interface EventLogger {
    void logEvent(Event event);
}
