package factory;

import domain.*;
import services.ReservationServiceImpl;
import services.ReservationServiceInterface;

import java.util.List;
import java.util.Optional;

public class BookingSystemAgentFactory {
    private static final ReservationServiceInterface reservationService = new ReservationServiceImpl();
    // 1.View list of airports
    // 2. View list of airlines
    // 3. view list of flights between a departure and destination
    // 4. view list of passenger and reservations made for them "by this agent"
    // 5. view detail of reservation
    // 6. make a reservation
    // 7. cancel a reservation
    // 8. purchase a reservation (return multiple tickets)
    public static void viewListOfAirport() {
        System.out.println("===viewListOfAirport===");
        for (Airport airport: reservationService.findAllAirports()) {
            System.out.println(airport.toString());
        }
    }

    public static void viewListOfAirLine(String airportCode) {
        System.out.println("===viewListOfAirLine===");
        List<Airline> alList = reservationService.findAirlinesByAirportCode(airportCode);
        for(Airline airline : alList){
            System.out.println(airline.toString());
        }
    }

    public static void viewListOfFlights(String departure, String arrival, String date) {
        System.out.println("===viewListOfFlights===");
        List<Flight> searchFlight = reservationService.findFlightsFromTo(departure, arrival, date);
        for (Flight flight: searchFlight) {
            System.out.println(flight.toString());
        }
    }

    public static void viewListPassengerAndReservation(Agent agent) {
        System.out.println("===viewListPassengerAndReservation===");
        if(agent != null){
            List<Passenger> passengerList = reservationService.findPassengersByAgentCode(agent.getCode());
            for(Passenger p : passengerList) {
                System.out.println(p.toString());
                List<Reservation> reservationList = reservationService.findReservationsByPassengerId(p.getId());
                for(Reservation r : reservationList) {
                    if(r.getAgent() != null && r.getAgent().getCode().equals(agent.getCode())){
                        System.out.println("> " + r.toString());
                    }
                }
            }
        }
    }

    public static void viewDetailOfReservation(Passenger passenger, String reservationCode, String agentCode) {
        System.out.println("===viewDetailOfReservation===");
        Reservation reservation = reservationService.findReservationsByReservationCode(reservationCode);
        if (reservation != null && reservation.getAgent().getCode().equals(reservationCode)
        && reservation.getPassenger().getId().equals(passenger.getId())) {
            System.out.println(reservation.toString());
        } else {
            System.out.println("There is no reservation with code " + reservationCode);
        }

    }

    public static void makeAReservation(Agent agent, Passenger passenger, List<Flight> flightList) {
        System.out.println("\n===makeAReservation===");
        if(agent != null && passenger != null && flightList != null) {
            Reservation reservation = reservationService.createReservation(agent,passenger,flightList);
            if (reservation != null) {
                System.out.println(reservation.toString());
            }
        }
        else{
            if(agent == null){
                System.out.println("[INFO]: Cannot make reservation with agent=null!");
            }
            else if(passenger == null){
                System.out.println("[INFO]: Cannot make reservation with passenger=null!");
            }
            else{
                System.out.println("[INFO]: Cannot make reservation with flightList=null!");
            }
        }
    }

    public static void cancelAReservation(String reservationCode) {
        System.out.println("===cancelAReservation===");
        reservationService.cancelReservation(reservationCode);
        System.out.println("Reservation code " + reservationCode + " has been canceled");
    }

    public static void purchaseReservation(String reservation_code) {
        System.out.println("\n===purchaseReservation===");
        List<Ticket> tickList = reservationService.confirmReservation(reservation_code);
        if(tickList != null) {
            for (Ticket t : tickList) {
                System.out.println(t.toString());
            }
        }
        else{
            System.out.println("[INFO]: Cannot make purchase with the given reservation_code!");
        }
    }
}
