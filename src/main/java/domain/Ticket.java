package domain;

import java.util.UUID;

public class Ticket {
    private final String id;
    private final String number;
    private final String reservationCode;
    private final Flight flight;

    public Ticket(String reservation_code, Flight flight) {
        this.id = UUID.randomUUID().toString();
        this.number = UUID.randomUUID().toString().replace("-", "").substring(0, 20).toUpperCase();
        this.reservationCode = reservation_code;
        this.flight = flight;
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public Flight getFlight() {
        return flight;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", reservationCode='" + reservationCode + '\'' +
                ", reservation=" + flight +
                '}';
    }
}
