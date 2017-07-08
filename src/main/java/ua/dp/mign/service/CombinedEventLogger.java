package ua.dp.mign.service;

import com.google.common.collect.ImmutableList;
import ua.dp.mign.model.Event;

import java.util.List;

public final class CombinedEventLogger implements EventLogger {

    private final List<EventLogger> loggers;

    public CombinedEventLogger(Iterable<EventLogger> loggers) {
        this.loggers = ImmutableList.copyOf(loggers);
    }

    public void logEvent(Event event) {
        for (EventLogger logger : this.loggers) {
            logger.logEvent(event);
        }
    }
}
