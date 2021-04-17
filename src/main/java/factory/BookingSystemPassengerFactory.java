package factory;

import domain.*;
import services.DatabaseAccessImpl;
import services.ReservationServiceImpl;
import services.ReservationServiceInterface;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class BookingSystemPassengerFactory {
    private static final ReservationServiceInterface reservationService = new ReservationServiceImpl();
    public static void generateReservation(Passenger passenger, Agent agent) {
        Reservation reservation1 = new Reservation(passenger, null);
        DatabaseAccessImpl.getInstance().addReservation(reservation1);

        Reservation reservation2 = new Reservation(passenger, null);
        DatabaseAccessImpl.getInstance().addReservation(reservation2);

        Reservation reservation3 = new Reservation(passenger, null);
        DatabaseAccessImpl.getInstance().addReservation(reservation3);
    }
    // 1.View list of airports
    // 2. View list of airlines
    // 3. view list of flights between a departure and destination
    // 4. view list of own reservations
    // 5. view detail of reservation
    // 6. make a reservation
    // 7. cancel a reservation
    // 8. purchase a reservation (return multiple tickets)
    public static void viewListOfAirport() {
        System.out.println("------------------------");
        System.out.println("\n 1. viewListOfAirport");
        for (Airport airport: reservationService.findAllAirports()) {
            System.out.println(airport.toString());
        }
    }

    public static void viewListOfAirLine(String airportCode) {
        System.out.println("------------------------");
        System.out.println("\n 2. viewListOfAirLine");
        List<Airline> alList = reservationService.findAirlinesByAirportCode(airportCode);
        if(alList != null) {
            for (Airline airline : alList) {
                System.out.println(airline.toString());
            }
        }
        else{
            System.out.println("[INFO]: Cannot view airline with airlineList=null!");
        }
    }

    public static void viewListOfFlights(String departure, String arrival, String date) {
        System.out.println("------------------------");
        System.out.println("\n 3. viewListOfFlights");

        List<Flight> searchFlight = reservationService.findFlightsFromTo(departure, arrival, date);
        for (Flight flight: searchFlight) {
            System.out.println(flight.toString());
        }
    }

    public static void viewListOfOwnReservation(Passenger passenger) {
        System.out.println("------------------------");
        System.out.println("\n 4. viewListOfOwnReservation");
        if(passenger != null) {
            List<Reservation> reservations = reservationService.findReservationsByPassengerId(passenger.getId());
            for (Reservation r : reservations) {
                System.out.println(r.toString());
            }
        }
        else{
            System.out.println("[INFO]: Cannot view reservation with passenger=null!");
        }
    }

    public static void viewDetailOfReservation(Passenger passenger, String reservationCode) {
        System.out.println("------------------------");
        System.out.println("\n 5. viewDetailOfReservation");
        List<Reservation> reservations = reservationService.findReservationsByPassengerId(passenger.getId());
        Optional<Reservation> reservation = reservations
                .stream()
                .filter((Reservation res) -> res.getCode().equals(reservationCode))
                .findFirst();
        if (reservation.isPresent()) {
            System.out.println(reservation.toString());
        } else {
            System.out.println("There are no reservation code `" + reservationCode + "` for passenger " + passenger.getFirstName());
        }
    }

    public static void makeAReservation(Passenger passenger, List<Flight> flightList) {
        System.out.println("------------------------");
        System.out.println("\n 6. makeAReservation");
        if(passenger != null && flightList != null) {
            Reservation reservation = reservationService.createReservation(passenger, flightList);
            if (reservation != null) {
                System.out.println(reservation.toString());
            }
        }
        else{
            if(passenger == null){
                System.out.println("[INFO]: Cannot make reservation with passenger=null!");
            }
            else{
                System.out.println("[INFO]: Cannot make reservation with flightList=null!");
            }
        }
    }

    public static void cancelAReservation(Passenger passenger, String reservationCode) {
        System.out.println("------------------------");
        System.out.println("\n 7. cancelAReservation");
        reservationService.cancelReservation(reservationCode);
        System.out.println("Removed reservation with code: " + reservationCode);
    }

    public static void purchaseReservation(String reservation_code) {
        System.out.println("\n 8. purchaseReservation");
        System.out.println("------------------------");
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
