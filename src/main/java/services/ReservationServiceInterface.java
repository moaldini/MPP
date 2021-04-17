package services;

import domain.*;

import java.util.List;
import java.util.Optional;

public interface ReservationServiceInterface {
    List<Airport> findAllAirports();

    Optional<Airport> findAirportByAirportCode(String airportCode);

    List<Airport> findAirportsByCity(String city); // Airport(s) of a city, e.g. Chicago has two major airports

    List<Airline> findAirlinesByAirportCode(String airportCode);

    List<Flight> findFlightsFromTo(String departure, String arrival, String date);

    List<Reservation> findReservationsByPassengerId(String passengerId);

    Reservation findReservationsByReservationCode(String reservationCode);

    List<Passenger> findPassengersByAgentCode(String agentCode);

    Reservation createReservation(Passenger passenger, List<Flight> flights); // Passenger reserves

    Reservation createReservation(Agent agent, Passenger passenger, List<Flight> flights); // Agent reserves

    List<Ticket> confirmReservation(String reservation_code);

    void cancelReservation(String reservationCode);
}
