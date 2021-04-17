import com.github.javafaker.Faker;
import domain.Reservation;
import factory.BookingSystemAgentFactory;
import factory.BookingSystemFactory;
import factory.BookingSystemPassengerFactory;
import domain.Agent;
import domain.Passenger;
import services.DatabaseAccessImpl;
import domain.Flight;
import services.ReservationServiceImpl;

import java.time.LocalDate;
import java.util.List;

public class BookingSystemApp {
    public static void main(String[] args) {
        // Load dummy data
        DatabaseAccessImpl.getInstance().fillData();

        //Passenger
        Passenger passenger = BookingSystemFactory.createPassenger();
        Passenger passenger1 = BookingSystemFactory.createPassenger();

        //agent
        Agent agent = BookingSystemFactory.createAgent("Milo");

        //flightList
        List<Flight> flightList = DatabaseAccessImpl.getInstance().getListOfFlight();

        System.out.println("==========================================");
        System.out.println("============     PASSENGER      ==========");
        System.out.println("==========================================");
        // 1.View list of airports
        BookingSystemPassengerFactory.viewListOfAirport();

        // 2. View list of airlines
        BookingSystemPassengerFactory.viewListOfAirLine("LAX");

        // 3. view list of flights between a departure and destination
        BookingSystemPassengerFactory.viewListOfFlights("LAX", "MIX", "2020-10-10");

        // 4. view list of own reservations
        BookingSystemPassengerFactory.makeAReservation(passenger1, flightList);
        BookingSystemPassengerFactory.viewListOfOwnReservation(passenger1);

        // 5. view detail of reservation
        BookingSystemPassengerFactory.generateReservation(passenger, agent);
        Reservation reservation = DatabaseAccessImpl.getInstance().getListOfReservation().get(0);
        BookingSystemPassengerFactory.viewDetailOfReservation(passenger, reservation.getCode());

        // 6. make a reservation
        BookingSystemPassengerFactory.makeAReservation(passenger, flightList);

        // 7. cancel a reservation
        BookingSystemPassengerFactory.cancelAReservation(passenger, reservation.getCode());

        // 8. purchase a reservation (return multiple tickets)
        reservation = DatabaseAccessImpl.getInstance().getListOfReservation().get(2);
        BookingSystemPassengerFactory.purchaseReservation(reservation.getCode());

        System.out.println("==========================================");
        System.out.println("============     AGENT      ==============");
        System.out.println("==========================================");
        //Agent
        // 1.View list of airports
        BookingSystemAgentFactory.viewListOfAirport();

        // 2. View list of airlines
        BookingSystemAgentFactory.viewListOfAirLine("LAX");

        // 3. view list of flights between a departure and destination
        BookingSystemAgentFactory.viewListOfFlights("LAX", "MIX", LocalDate.now().toString());

        // 4. view list of passenger and reservations made for them "by this agent"
        BookingSystemAgentFactory.viewListPassengerAndReservation(null);

        // 5. view detail of reservation
        BookingSystemAgentFactory.viewDetailOfReservation(passenger, reservation.getCode(), agent.getCode());

        // 6. make a reservation
        List<Agent> agentList = DatabaseAccessImpl.getInstance().getListOfAgent();
        Agent depatosAgent = agentList.get(0);
        //List<Flight> flightList = DatabaseAccessImpl.getInstance().getListOfFlight();
        BookingSystemAgentFactory.makeAReservation(depatosAgent,depatosAgent.getListOfPassenger().get(0), flightList);
        BookingSystemAgentFactory.makeAReservation(depatosAgent,depatosAgent.getListOfPassenger().get(0), flightList);

        // 4. view list of passenger and reservations made for them "by this agent"
        BookingSystemAgentFactory.viewListPassengerAndReservation(depatosAgent);

        // 7. cancel a reservation
        BookingSystemAgentFactory.cancelAReservation(reservation.getCode());

        // 8. purchase a reservation (return multiple tickets)
        reservation = DatabaseAccessImpl.getInstance().getListOfReservation().get(2);
        BookingSystemAgentFactory.purchaseReservation(reservation.getCode());
    }
}
