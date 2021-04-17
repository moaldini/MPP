package domain;

import java.util.UUID;

public class Crew {
    private final String id;
    private final String name;

    public Crew(String name) {
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
        return "Crew{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
