package ua.dp.mign.service;

import com.google.common.collect.Lists;
import ua.dp.mign.model.Event;

import java.util.List;

public class BufferedEventLogger implements EventLogger {
    private final int bufferSize;
    private final List<Event> events;
    private final EventLogger logger;

    public BufferedEventLogger(int bufferSize, EventLogger logger) {
        this.bufferSize = bufferSize;
        this.logger = logger;
        this.events = Lists.newLinkedList();
    }

    public void destroy() {
        if (!events.isEmpty()) {
            pushBufferedEvents();
        }
    }

    public void logEvent(Event event) {
        events.add(event);
        if (events.size() == bufferSize) {
            pushBufferedEvents();
        }
    }

    private void pushBufferedEvents() {
        for (Event e : this.events) {
            logger.logEvent(e);
        }
        events.clear();
    }
}
