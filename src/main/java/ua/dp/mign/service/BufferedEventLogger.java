package ua.dp.mign.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.dp.mign.model.Event;

import javax.annotation.PreDestroy;
import java.util.List;

@Component("bufferedFileLogger")
public class BufferedEventLogger implements EventLogger {
    private final int bufferSize;
    private final List<Event> events;
    private final EventLogger logger;

    @Autowired
    public BufferedEventLogger(@Value("3") int bufferSize, @Qualifier("fileLogger") EventLogger logger) {
        this.bufferSize = bufferSize;
        this.logger = logger;
        this.events = Lists.newLinkedList();
    }

    @PreDestroy
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
