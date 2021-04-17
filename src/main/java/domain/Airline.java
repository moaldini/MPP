package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Airline {
    private final String id;
    private final String code;
    private final String name;
    private final String history;
    private final List<Airport> airports;

    public Airline(String code, String name, String history) {
        this.airports = new ArrayList<>();
        this.id = UUID.randomUUID().toString();
        this.code = code;
        this.name = name;
        this.history = history;
    }

    public void addAirport(Airport airport) {
        this.airports.add(airport);
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getHistory() {
        return history;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", history='" + history + '\'' +
                ", airports=" + airports +
                '}';
    }
}
