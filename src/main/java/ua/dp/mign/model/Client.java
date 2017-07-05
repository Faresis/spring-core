package ua.dp.mign.model;

public class Client {
    private String id;
    private String fullName;

    public Client(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
}
