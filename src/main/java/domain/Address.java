package domain;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.UUID;

public class Address {
    private final String id;
    private final String street;
    private final String city;
    private final String state;
    private final String zip;

    public Address() {
        Faker faker = new Faker(new Locale("us-en"));
        this.id = UUID.randomUUID().toString();
        this.street = faker.address().streetAddress();
        this.city = faker.address().city();
        this.state = faker.address().state();
        this.zip = faker.address().zipCode();
    }

    public String getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
