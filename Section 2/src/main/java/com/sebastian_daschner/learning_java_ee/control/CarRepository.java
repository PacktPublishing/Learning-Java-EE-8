package com.sebastian_daschner.learning_java_ee.control;

import com.sebastian_daschner.learning_java_ee.entity.Car;
import com.sebastian_daschner.learning_java_ee.entity.Color;
import com.sebastian_daschner.learning_java_ee.entity.EngineType;

import java.util.List;

import static java.util.Arrays.asList;

public class CarRepository {

    public void store(Car car) {
        // dummy error on creation
        System.out.println("persisted car = " + car);
    }

    public List<Car> loadCars() {
        // dummy creation
        return asList(
                createCar("X123A234", Color.RED, EngineType.DIESEL),
                createCar("X234B345", Color.BLACK, EngineType.ELECTRIC),
                createCar("X345C456", Color.GREY, EngineType.PETROL)
        );
    }

    private static Car createCar(String identifier, Color color, EngineType engineType) {
        Car car = new Car();
        car.setIdentifier(identifier);
        car.setColor(color);
        car.setEngineType(engineType);
        return car;
    }

}
