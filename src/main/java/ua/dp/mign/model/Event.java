package ua.dp.mign.model;

import com.google.common.base.MoreObjects;

import java.text.DateFormat;
import java.util.Date;

public class Event {
    private final long id;
    private String message;
    private final Date date;
    private final DateFormat dateFormat;

    public Event(Date date, DateFormat dateFormat) {
        this.id = System.nanoTime();
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public long getId() {
        return id;
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
