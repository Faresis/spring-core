package ua.dp.mign.model;

import com.google.common.base.MoreObjects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;

@Component("event")
@Scope("prototype")
public class Event {

    private final long id;
    private String message;
    private final Date date;
    private final DateFormat dateFormat;

    public Event(@Value("#{T(System).nanoTime()}") long id,
                 @Value("#{new java.util.Date()}") Date date,
                 @Value("#{T(java.text.DateFormat).getDateTimeInstance()}") DateFormat dateFormat) {

        this.id = id;
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("message", message)
                .add("date", dateFormat.format(date))
                .toString();
    }
}
