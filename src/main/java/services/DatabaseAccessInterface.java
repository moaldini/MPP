package services;

import domain.*;

import java.util.ArrayList;
import java.util.List;

public interface DatabaseAccessInterface {
    ArrayList<Airline> getListOfAirline();
    ArrayList<Airport> getListOfAirport();
    ArrayList<Flight> getListOfFlight();
    ArrayList<Reservation> getListOfReservation();
    Reservation getListOfReservationByCode(String reservation_code);
    ArrayList<Passenger> getListOfPassenger();
    ArrayList<Agent> getListOfAgent();
    Agent getAgentByCode(String agent_code);
    void addReservation(Reservation reservation);
    void removeReservationByCode(String reservationCode);
    void addPurchasedReservation(String reservationCode, List<Ticket> tickets);
    List<Ticket> getListTicketByReservationCode(String reservationCode);
}
