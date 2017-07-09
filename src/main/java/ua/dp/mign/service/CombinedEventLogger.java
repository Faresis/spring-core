package ua.dp.mign.service;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.dp.mign.model.Event;

import java.util.List;

@Component("combinedLogger")
public final class CombinedEventLogger implements EventLogger {

    private final List<EventLogger> loggers;

    @Autowired
    public CombinedEventLogger(@Qualifier("combinedLoggers") Iterable<EventLogger> loggers) {
        this.loggers = ImmutableList.copyOf(loggers);
    }

    public void logEvent(Event event) {
        for (EventLogger logger : this.loggers) {
            logger.logEvent(event);
        }
    }
}
