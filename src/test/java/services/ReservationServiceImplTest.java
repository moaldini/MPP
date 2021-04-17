package services;

import domain.Flight;
import domain.Passenger;
import factory.BookingSystemFactory;
import factory.BookingSystemPassengerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceImplTest {
    ReservationServiceImpl reservationService;
    Passenger passenger;
    List<Flight> flightList;
    DatabaseAccessImpl da = DatabaseAccessImpl.getInstance();

    @BeforeEach
    void setUp() {
        reservationService = new ReservationServiceImpl();
        DatabaseAccessImpl.getInstance().fillData();

        flightList = DatabaseAccessImpl.getInstance().getListOfFlight();
        passenger = BookingSystemFactory.createPassenger();
        da.listOfPassengers.put(passenger.getId(), passenger);
        BookingSystemPassengerFactory.makeAReservation(BookingSystemFactory.createPassenger(), flightList);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllAirports() {
        assertEquals(DatabaseAccessImpl.getInstance().listOfAirports.size(),
                reservationService.findAllAirports().size());
    }

    @Test
    void findAirportByAirportCodeNotExit() {
        assertTrue(reservationService.findAirlinesByAirportCode("AAA").isEmpty());
    }

    @Test
    void findAirportByAirportCodeExit() {
        assertFalse(reservationService.findAirlinesByAirportCode(da.getListOfAirport().get(0).getCode()).isEmpty());
    }

    @Test
    void findAirportsByCity() {
        assertTrue(reservationService.findAirportsByCity("LAX").isEmpty());
    }

    @Test
    void findAirlinesByAirportCode() {
        assertFalse(reservationService.findAirlinesByAirportCode("LAX").isEmpty());
    }

    @Test
    void findFlightsFromTo() {
        assertFalse(reservationService.findFlightsFromTo("LAX", "MIX", LocalDate.now().toString()).isEmpty());
    }

    @Test
    void findFlightsFromToFalse() {
        assertTrue(reservationService.findFlightsFromTo("LAX", "VNA", LocalDate.now().toString()).isEmpty());
    }

    @Test
    void findReservationsByPassengerId() {
        assertEquals(0, reservationService.findReservationsByPassengerId(da.getListOfPassenger().get(0).getId()).size());
    }

    @Test
    void findReservationsByPassengerIdTrue() {
        reservationService.createReservation(passenger, null);
        assertEquals(1, reservationService.findReservationsByPassengerId(passenger.getId()).size());
    }

    @Test
    void findReservationsByReservationCode() {
        assertNotNull(reservationService.findReservationsByReservationCode(da.getListOfReservation().get(0).getCode()));
    }

    @Test
    void findPassengersByAgentCode() {
    }

    @Test
    void testCreateReservation() {
        assertEquals(passenger.getId(), reservationService.createReservation(passenger, flightList).getPassenger().getId());
    }

    @Test
    void confirmReservation() {
    }

    @Test
    void cancelReservation() {
        reservationService.cancelReservation(da.getListOfReservation().get(0).getCode());
        assertEquals(5, da.getListOfReservation().size());
    }
}