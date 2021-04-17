package domain;

import java.util.UUID;

public class Pilot {
    private final String id;
    private final String name;

    public Pilot(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Pilot{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
