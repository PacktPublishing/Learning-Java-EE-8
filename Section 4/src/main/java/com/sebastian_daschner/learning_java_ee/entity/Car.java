package com.sebastian_daschner.learning_java_ee.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import static com.sebastian_daschner.learning_java_ee.entity.Car.FIND_ALL;

@Entity
@NamedQuery(name = FIND_ALL, query = "select c from Car c")
public class Car {

    public static final String FIND_ALL = "Car.findAll";

    @Id
    private String identifier;
    private Color color;
    private EngineType engineType;

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

    @Override
    public String toString() {
        return "Car{" +
                "identifier='" + identifier + '\'' +
                ", color=" + color +
                ", engineType=" + engineType +
                '}';
    }
}
