package com.sebastian_daschner.learning_java_ee.boundary;

import com.sebastian_daschner.learning_java_ee.control.CarCache;
import com.sebastian_daschner.learning_java_ee.control.CarFactory;
import com.sebastian_daschner.learning_java_ee.control.ProcessTracker;
import com.sebastian_daschner.learning_java_ee.control.Tracked;
import com.sebastian_daschner.learning_java_ee.entity.Car;
import com.sebastian_daschner.learning_java_ee.entity.Specification;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.function.Consumer;

@Stateless
public class CarManufacturer {

    @Inject
    CarFactory carFactory;

    @Inject
    CarCache carCache;

    @Inject
    Consumer<Throwable> fatalLogger;

    @Tracked(ProcessTracker.Category.MANUFACTURER)
    public Car manufactureCar(Specification specification) {
        Car car = carFactory.createCar(specification);
        carCache.cache(car);

        try {
            // do something that can fail
        } catch (Exception e) {
            fatalLogger.accept(e);
        }

        return car;
    }

    public List<Car> retrieveCars() {
        return carCache.retrieveCars();
    }

}
