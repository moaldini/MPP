package domain;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

public class Agent {
    private final String id;
    private final String code;
    private final String name;
    private final List<Passenger> passengerList;

    public Agent(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.code = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        this.passengerList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public List<Passenger> getListOfPassenger() {
        return this.passengerList;
    }

    public void addPassenger(Passenger passenger){
        this.passengerList.add(passenger);
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
