package services;

import com.github.javafaker.Faker;
import domain.*;

import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DatabaseAccessImpl implements DatabaseAccessInterface {
    HashMap<String, Airport> listOfAirports;
    HashMap<String, Airline> listOfAirlines;
    HashMap<String, Flight> listOfFlights;
    HashMap<String, Reservation> listOfReservations;
    HashMap<String, List<Ticket>> listOfTicket;
    HashMap<String, Passenger> listOfPassengers;
    HashMap<String, Agent> listOfAgents;

    private static final DatabaseAccessImpl databaseAccessInstance = new DatabaseAccessImpl();

    DatabaseAccessImpl() {
        this.listOfAirlines = new HashMap<>();
        this.listOfAirports = new HashMap<>();
        this.listOfFlights = new HashMap<>();
        this.listOfReservations = new HashMap<>();
        this.listOfTicket = new HashMap<>();
        this.listOfPassengers = new HashMap<>();
        this.listOfAgents = new HashMap<>();
    }

    public void fillData() {
        //fill airport
        Airport airport1 = new Airport("LAX", "Los Angeles");
        airport1.setAddress(new Address());
        this.listOfAirports.put("LAX", airport1);

        Airport airport2 = new Airport("MIX", "Miami");
        airport2.setAddress(new Address());
        this.listOfAirports.put("MIX", airport2);

        Airport airport3 = new Airport("KUL", "Kuala Lumpur");
        airport3.setAddress(new Address());
        this.listOfAirports.put("KUL", airport3);

        // fill airline
        Airline airline1 = new Airline("UA", "United Airlines", "Cool");
        airline1.addAirport(this.listOfAirports.get("LAX"));
        airline1.addAirport(this.listOfAirports.get("MIX"));
        airline1.addAirport(this.listOfAirports.get("KUL"));
        this.listOfAirlines.put("UA", airline1);

        Airline airline2 = new Airline("JAL", "Japan Airlines", "Good");
        airline2.addAirport(this.listOfAirports.get("LAX"));
        airline2.addAirport(this.listOfAirports.get("MIX"));
        this.listOfAirlines.put("JAL", airline2);

        Airline airline3 = new Airline("VNA", "Vietnam Airlines", "Nice");
        airline3.addAirport(this.listOfAirports.get("LAX"));
        airline3.addAirport(this.listOfAirports.get("MIX"));
        this.listOfAirlines.put("VNA", airline3);

        //add airline
        listOfAirports.get("LAX").addAirline(airline1);
        listOfAirports.get("LAX").addAirline(airline2);
        listOfAirports.get("LAX").addAirline(airline3);
        listOfAirports.get("MIX").addAirline(airline2);
        listOfAirports.get("MIX").addAirline(airline3);
        listOfAirports.get("KUL").addAirline(airline2);
        listOfAirports.get("KUL").addAirline(airline3);

        // fill flights
        FlightNumber flightNumber1 = new FlightNumber("LAX-MIX",
                                          100,
                                          this.listOfAirports.get("LAX"),
                                          this.listOfAirports.get("MIX"),
                                          LocalTime.now().toString(),
                                          LocalTime.now().toString());

        Flight flight1 = new Flight(flightNumber1, LocalDate.now().toString());
        flight1.addCrew(new Crew("Crew1.1"));
        flight1.addCrew(new Crew("Crew1.2"));
        flight1.addPilot(new Pilot("Pilot1"));
        this.listOfFlights.put("LAX-MIX", flight1);

        FlightNumber flightNumber2 = new FlightNumber("LAX-KUL",
                                        120,
                                        this.listOfAirports.get("LAX"),
                                        this.listOfAirports.get("KUL"),
                                        LocalTime.now().toString(),
                                        LocalTime.now().toString());

        Flight flight2 = new Flight(flightNumber2, LocalDate.now().toString());
        flight2.addCrew(new Crew("Crew2.1"));
        flight2.addCrew(new Crew("Crew2.2"));
        flight2.addPilot(new Pilot("Pilot2"));
        this.listOfFlights.put("LAX-KUL", flight2);

        FlightNumber flightNumber3 = new FlightNumber("MIX-KUL",
                                        200,
                                        this.listOfAirports.get("MIX"),
                                        this.listOfAirports.get("KUL"),
                                        LocalTime.now().toString(),
                                        LocalTime.now().toString());

        Flight flight3 = new Flight(flightNumber3, LocalDate.now().toString());
        flight3.addCrew(new Crew("Crew3.1"));
        flight3.addCrew(new Crew("Crew3.2"));
        flight3.addPilot(new Pilot("Pilot2"));
        this.listOfFlights.put("MIX-KUL", flight3);


        // add agent
        Agent agent1 = new Agent("Depatos");
        Passenger passenger1 = new Passenger("Vu","Pham","avpham@miu.edu");
        agent1.addPassenger(passenger1);
        this.listOfAgents.put(agent1.getCode(),agent1);
    }

    public static DatabaseAccessImpl getInstance() {
        return databaseAccessInstance;
    }

    @Override
    public void addReservation(Reservation reservation) {
        this.listOfReservations.put(reservation.getCode(), reservation);
    }

    @Override
    public void removeReservationByCode(String reservationCode) {
        this.listOfReservations.remove(reservationCode);
    }

    @Override
    public void addPurchasedReservation(String reservationCode, List<Ticket>tickets) {
        this.listOfTicket.put(reservationCode, tickets);
    }

    @Override
    public List<Ticket> getListTicketByReservationCode(String reservationCode) {
        return this.listOfTicket.get(reservationCode);
    }

    @Override
    public ArrayList<Airline> getListOfAirline() {
        return new ArrayList<>(this.listOfAirlines.values());
    }

    @Override
    public ArrayList<Airport> getListOfAirport() {
        return new ArrayList<>(this.listOfAirports.values());
    }

    @Override
    public ArrayList<Flight> getListOfFlight() {
        return new ArrayList<>(this.listOfFlights.values());
    }

    @Override
    public ArrayList<Passenger> getListOfPassenger() {
        return new ArrayList<>(this.listOfPassengers.values());
    }

    @Override
    public ArrayList<Agent> getListOfAgent() { return new ArrayList<>(this.listOfAgents.values());}

    @Override
    public ArrayList<Reservation> getListOfReservation() {
        return new ArrayList<>(this.listOfReservations.values());
    }

    @Override
    public Reservation getListOfReservationByCode(String reservation_code){
        return this.listOfReservations.get(reservation_code);
    }

    @Override
    public Agent getAgentByCode(String agent_code) {
        return this.listOfAgents.get(agent_code);
    }

}
