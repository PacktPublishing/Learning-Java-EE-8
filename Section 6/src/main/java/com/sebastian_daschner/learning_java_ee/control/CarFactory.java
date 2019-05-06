package com.sebastian_daschner.learning_java_ee.control;

import com.sebastian_daschner.learning_java_ee.entity.Car;
import com.sebastian_daschner.learning_java_ee.entity.Specification;

import javax.inject.Inject;

public class CarFactory {

    @Inject
    IdentifierAccessor identifierAccessor;

    public Car createCar(Specification specification) {
        Car car = new Car();
        car.setIdentifier(identifierAccessor.retrieveCarIdentification(specification));
        car.setColor(specification.getColor());
        car.setEngineType(specification.getEngineType());
        return car;
    }

}