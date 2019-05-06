package com.sebastian_daschner.learning_java_ee.cars.control;

import com.sebastian_daschner.learning_java_ee.cars.entity.Car;
import com.sebastian_daschner.learning_java_ee.cars.entity.Specification;

import java.util.UUID;

public class CarFactory {

    public Car createCar(Specification specification) {
        Car car = new Car();
        car.setIdentifier(UUID.randomUUID().toString());
        car.setColor(specification.getColor());
        car.setEngineType(specification.getEngineType());
        return car;
    }

}