package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Flight {
    private final List<Crew> crews;
    private Pilot pilot;

    private List<Ticket> tickets;
    private LocalDate date;
    private List<Passenger> passengers;
    private FlightNumber flightNumber;

    public Flight(FlightNumber flightNumber, String date) {
        //this.departureAirport = departureAirport;
        //this.arrivalAirport = arrivalAirport;
        this.flightNumber = flightNumber;
        this.passengers = new ArrayList<>();
        this.date = LocalDate.parse(date);
        crews = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Flight{" +
                "departureAirport=" + flightNumber.getDepartureAirport() +
                ", arrivalAirport=" + flightNumber.getArrivalAirport() +
                ", crews=" + crews +
                ", pilot=" + pilot +
                ", departureTime=" + flightNumber.getDepartureTime() +
                ", arrivalTime=" + flightNumber.getArrivalTime() +
                ", tickets=" + tickets +
                ", date=" + date +
                ", passengers=" + passengers +
                '}';
    }

    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }

    public void addCrew(Crew crew) {
        this.crews.add(crew);
    }

    public void addPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public List<Crew> getCrews() {
        return crews;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public Airport getDepartureAirport() {
        return flightNumber.getDepartureAirport();
    }

    public Airport getArrivalAirport() {
        return flightNumber.getArrivalAirport();
    }

    public LocalTime getDepartureTime() { return flightNumber.getDepartureTime(); }

    public LocalTime getArrivalTime() { return flightNumber.getArrivalTime(); }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public LocalDate getDate() { return date; }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public FlightNumber getFlightNumber() { return flightNumber; }
}
