package services;

import domain.*;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class ReservationServiceImpl implements ReservationServiceInterface {
    private List<Airport> airports = DatabaseAccessImpl.getInstance().getListOfAirport();
    private List<Airline> airlines = DatabaseAccessImpl.getInstance().getListOfAirline();
    private List<Flight> flights = DatabaseAccessImpl.getInstance().getListOfFlight();
    private List<Reservation> reservations = DatabaseAccessImpl.getInstance().getListOfReservation();
    private List<Passenger> passengers = DatabaseAccessImpl.getInstance().getListOfPassenger();
    private List<Agent> agents = DatabaseAccessImpl.getInstance().getListOfAgent();

    private void reloadData() {
        airports = DatabaseAccessImpl.getInstance().getListOfAirport();
        airlines = DatabaseAccessImpl.getInstance().getListOfAirline();
        flights = DatabaseAccessImpl.getInstance().getListOfFlight();
        reservations = DatabaseAccessImpl.getInstance().getListOfReservation();
        passengers = DatabaseAccessImpl.getInstance().getListOfPassenger();
    }

    @Override
    public List<Airport> findAllAirports() {
        return airports;
    }

    @Override
    public Optional<Airport> findAirportByAirportCode(String airportCode) {
        reloadData();
        return airports
                .stream()
                .filter(airport -> airport.getCode().equals(airportCode))
                .findFirst();
    }

    @Override
    public List<Airport> findAirportsByCity(String city) {
        reloadData();
        return airports
            .stream()
            .filter(airport -> airport.getAddress().getCity().equals(city))
            .collect(Collectors.toList()); }

    @Override
    public List<Airline> findAirlinesByAirportCode(String airportCode) {
        reloadData();
        List<Airline> ret_airlines = new ArrayList<>();

        for(Airline al : airlines){
            List<Airport> apList = al.getAirports();
            for(Airport ap : apList){
                if(ap.getCode().equals(airportCode)){
                    ret_airlines.add(al);
                    break;
                }
            }
        }
        return ret_airlines;
    }

    @Override
    public List<Flight> findFlightsFromTo(String departure, String arrival, String date) {
        reloadData();
        return flights
                .stream()
                .filter((Flight flight) ->
                        flight.getDepartureAirport().getCode().equals(departure)
                                && flight.getArrivalAirport().getCode().equals(arrival))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findReservationsByPassengerId(String passengerId) {
        reloadData();
        return reservations.stream()
                .filter((Reservation res) -> res.getPassenger().getId().equals(passengerId))
                .collect(Collectors.toList());
    }

    public Reservation findReservationsByReservationCode(String reservationCode) {
        reloadData();
        List<Reservation> result = reservations.stream()
                .filter((reservation -> reservation.getCode().equals(reservationCode)))
                .collect(Collectors.toList());
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public List<Passenger> findPassengersByAgentCode(String agentCode) {
        //reloadData();
        Agent agent = DatabaseAccessImpl.getInstance().getAgentByCode(agentCode);
        if(agent != null) {
            return agent.getListOfPassenger();
        }
        return null;
    }

    @Override
    public Reservation createReservation(Passenger passenger, List<Flight> flights) {
        //reloadData();
        Reservation reservation = new Reservation(passenger, flights);
        DatabaseAccessImpl.getInstance().addReservation(reservation);
        return reservation;
    }

    @Override
    public Reservation createReservation(Agent agent, Passenger passenger, List<Flight> flights) {
        //reloadData();
        if (agent != null && passenger != null && flights != null) {
            Reservation reservation = new Reservation(agent, passenger, flights);
            DatabaseAccessImpl.getInstance().addReservation(reservation);
            return reservation;
        }
        return null;
    }

    @Override
    public List<Ticket> confirmReservation(String reservation_code) {
        //reloadData();
        List<Ticket> ticketList = new ArrayList<>();
        Reservation reservation = DatabaseAccessImpl.getInstance().getListOfReservationByCode(reservation_code);
        if(reservation != null) {
            List<Flight> flightList = reservation.getFlights();
            if (flightList == null) {
                return ticketList;
            }
            for (Flight flight : flightList) {
                ticketList.add(new Ticket(reservation_code, flight));
            }
            if (!ticketList.isEmpty()) {
                DatabaseAccessImpl.getInstance().addPurchasedReservation(reservation_code, ticketList);
            }
            DatabaseAccessImpl.getInstance().removeReservationByCode(reservation_code);
        }
        return ticketList;
    }

    @Override
    public void cancelReservation(String reservationCode) {
        reloadData();
        Reservation reservation = this.findReservationsByReservationCode(reservationCode);
        if (reservation != null) {
            DatabaseAccessImpl.getInstance().removeReservationByCode(reservationCode);
        }
    }
}
