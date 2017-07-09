package ua.dp.mign.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("client")
public final class Client {
    @Value("${id}")
    private String id;

    @Value("${name}")
    private String fullName;

    @Value("${greeting}")
    private String greeting;

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGreeting() {
        return greeting;
    }
}
