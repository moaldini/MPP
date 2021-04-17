package domain;

import java.time.LocalTime;
import java.util.UUID;

public class FlightNumber {
    private final String id;
    private final String number;
    private final int capacity;
    private final Airport departureAirport;
    private final Airport arrivalAirport;
    private final LocalTime departureTime;
    private final LocalTime arrivalTime;

    public FlightNumber(String number, int capacity, Airport departureAirport, Airport arrivalAirport, String departureTime, String arrivalTime) {
        this.id = UUID.randomUUID().toString();
        this.number = number;
        this.capacity = capacity;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = LocalTime.parse(departureTime);
        this.arrivalTime = LocalTime.parse(arrivalTime);
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public int getCapacity() {
        return capacity;
    }

    public LocalTime getDepartureTime() { return departureTime; }

    public LocalTime getArrivalTime() { return arrivalTime; }

    public Airport getDepartureAirport() { return departureAirport; }

    public Airport getArrivalAirport() { return arrivalAirport; }


    @Override
    public String toString() {
        return "FlightNumber{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
