package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Reservation {
    private final String id;
    private final String code;
    private final List<Flight> flights;
    private final Agent agent;
    private final Passenger passenger;

    public Reservation(Agent agent, Passenger passenger, List<Flight> flights) {
        this.id = UUID.randomUUID().toString();
        this.code = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
        this.agent = agent;
        this.passenger = passenger;
        this.flights = new ArrayList<>();
        if(flights != null) {
            this.flights.addAll(flights);
        }
    }

    public Reservation(Passenger passenger, List<Flight> flights) {
        this.id = UUID.randomUUID().toString();
        this.code = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
        this.agent = null;
        this.flights = new ArrayList<>();
        if(flights != null) {
            this.flights.addAll(flights);
        }
        this.passenger = passenger;
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public Agent getAgent() {
        return agent;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", flights=" + flights +
                ", passenger=" + passenger +
                ", agent=" + agent +
                '}';
    }
}
