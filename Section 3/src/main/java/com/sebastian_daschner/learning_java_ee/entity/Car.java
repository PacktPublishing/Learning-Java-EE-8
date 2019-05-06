package com.sebastian_daschner.learning_java_ee.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static com.sebastian_daschner.learning_java_ee.entity.Car.FIND_ALL;

@Entity
@Table(name = "cars")
@NamedQuery(name = FIND_ALL, query = "select c from Car c")
public class Car {

    public static final String FIND_ALL = "Car.findAll";

    @Id
    private String identifier;

    @Enumerated(EnumType.STRING)
    private Color color;

    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "car", nullable = false)
    private Set<Seat> seats = new HashSet<>();

    public Car() {
    }

    public Car(String identifier, Color color, EngineType engineType) {
        this.identifier = identifier;
        this.color = color;
        this.engineType = engineType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Car{" +
                "identifier='" + identifier + '\'' +
                ", color=" + color +
                ", engineType=" + engineType +
                '}';
    }
}
