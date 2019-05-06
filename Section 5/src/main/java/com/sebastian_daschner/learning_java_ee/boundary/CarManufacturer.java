package com.sebastian_daschner.learning_java_ee.boundary;

import com.sebastian_daschner.learning_java_ee.control.CarFactory;
import com.sebastian_daschner.learning_java_ee.control.CarProcessor;
import com.sebastian_daschner.learning_java_ee.control.CarRepository;
import com.sebastian_daschner.learning_java_ee.entity.Car;
import com.sebastian_daschner.learning_java_ee.entity.CarCreated;
import com.sebastian_daschner.learning_java_ee.entity.Specification;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
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
    CarProcessor carProcessor;

    @Inject
    Event<CarCreated> carCreated;

    @Resource
    ManagedExecutorService mes;

    public Car manufactureCar(Specification specification) {
        Car car = carFactory.createCar(specification);
        carRepository.store(car);
        carProcessor.processNewCarAsync(car);
        mes.execute(() -> carProcessor.processNewCar(car));
        carCreated.fireAsync(new CarCreated(car.getIdentifier()));
        return car;
    }

    public List<Car> retrieveCars() {
        return carRepository.loadCars();
    }

}
