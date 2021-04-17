package factory;

import com.github.javafaker.Faker;
import domain.Agent;
import domain.Passenger;

public class BookingSystemFactory {
    public static Passenger createPassenger() {
        Faker faker = new Faker();
        return new Passenger(faker.name().firstName(), faker.name().lastName(), faker.name().name() + "@gmail.com");
    }

    public static Agent createAgent(String name) {
        return new Agent(name);
    }
}
