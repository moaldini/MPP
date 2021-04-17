import domain.Reservation;
import factory.BookingSystemAgentFactory;
import factory.BookingSystemFactory;
import factory.BookingSystemPassengerFactory;
import domain.Agent;
import domain.Passenger;
import services.DatabaseAccessImpl;
import domain.Flight;
import java.util.List;
import services.ReservationServiceImpl;

import javax.xml.crypto.Data;

public class BookingSystemApp {
    public static void main(String[] args) {
        // Load dummy data
        DatabaseAccessImpl.getInstance().fillData();

        //Passenger
        Passenger passenger = BookingSystemFactory.createPassenger("Phu", "The", "phu@gmail.com");

        //agent
        Agent agent = BookingSystemFactory.createAgent("Milo");

        System.out.println("==========================================");
        System.out.println("============     PASSENGER      ==========");
        System.out.println("==========================================");
        // 1.View list of airports
        BookingSystemPassengerFactory.viewListOfAirport();

        // 2. View list of airlines
        BookingSystemPassengerFactory.viewListOfAirLine("LAX");

        // 3. view list of flights between a departure and destination
        BookingSystemPassengerFactory.viewListOfFlights("LAX", "MIX");

        // 4. view list of own reservations
        BookingSystemPassengerFactory.viewListOfOwnReservation(null);

        // 5. view detail of reservation
        BookingSystemPassengerFactory.generateReservation(passenger, agent);
        Reservation reservation = DatabaseAccessImpl.getInstance().getListOfReservation().get(0);
        BookingSystemPassengerFactory.viewDetailOfReservation(passenger, reservation.getCode());

        // 6. make a reservation
        List<Flight> flightList = DatabaseAccessImpl.getInstance().getListOfFlight();
        BookingSystemPassengerFactory.makeAReservation(passenger,flightList);

        // 7. cancel a reservation
        BookingSystemPassengerFactory.cancelAReservation(passenger, reservation.getCode());

        // 8. purchase a reservation (return multiple tickets)
        reservation = DatabaseAccessImpl.getInstance().getListOfReservation().get(2);
        BookingSystemPassengerFactory.purchaseReservation(reservation);

        System.out.println("==========================================");
        System.out.println("============     AGENT      ==============");
        System.out.println("==========================================");
        //Agent
        // 1.View list of airports
        BookingSystemAgentFactory.viewListOfAirport();

        // 2. View list of airlines
        BookingSystemAgentFactory.viewListOfAirLine("LAX");

        // 3. view list of flights between a departure and destination
        BookingSystemAgentFactory.viewListOfFlights("LAX", "MIX");

        // 4. view list of passenger and reservations made for them "by this agent"
        BookingSystemAgentFactory.viewListPassengerAndReservation(null);

        // 5. view detail of reservation
        BookingSystemAgentFactory.viewDetailOfReservation(reservation.getCode());

        // 6. make a reservation
        BookingSystemAgentFactory.makeAReservation(null);

        // 7. cancel a reservation
        BookingSystemAgentFactory.cancelAReservation(reservation.getCode());

        // 8. purchase a reservation (return multiple tickets)
        BookingSystemPassengerFactory.purchaseReservation(reservation);
    }
}
