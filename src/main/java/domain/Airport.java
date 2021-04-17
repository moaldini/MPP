package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Airport {
    private final String id;
    private final String code;
    private final String name;
    private Address address;
    private final List<Airline> airlines;

    public Airport(String code, String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.code = code;
        this.airlines = new ArrayList<>();
    }

    public void addAirline(Airline airline) {
        this.airlines.add(airline);
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public List<Airline> getAirlines() {
        return airlines;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
