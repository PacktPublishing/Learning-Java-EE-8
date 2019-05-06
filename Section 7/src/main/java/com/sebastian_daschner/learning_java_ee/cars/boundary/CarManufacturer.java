package com.sebastian_daschner.learning_java_ee.cars.boundary;

import com.sebastian_daschner.learning_java_ee.cars.control.CarFactory;
import com.sebastian_daschner.learning_java_ee.cars.entity.Specification;
import com.sebastian_daschner.learning_java_ee.cars.control.CarRepository;
import com.sebastian_daschner.learning_java_ee.cars.entity.Car;
import com.sebastian_daschner.learning_java_ee.cars.entity.CarCreated;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CarManufacturer {

    @Inject
    CarFactory carFactory;

    @Inject
    CarRepository carRepository;

    @Inject
    Event<CarCreated> carCreated;

    public Car manufactureCar(Specification specification) {
        Car car = carFactory.createCar(specification);
        carRepository.store(car);
        carCreated.fire(new CarCreated(car.getIdentifier()));
        return car;
    }

    public List<Car> retrieveCars() {
        return carRepository.loadCars();
    }

}
