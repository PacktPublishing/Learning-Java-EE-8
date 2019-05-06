package com.sebastian_daschner.learning_java_ee.boundary;

import com.sebastian_daschner.learning_java_ee.control.CarFactory;
import com.sebastian_daschner.learning_java_ee.entity.Car;
import com.sebastian_daschner.learning_java_ee.entity.Specification;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CarManufacturer {

    @Inject
    CarFactory carFactory;

    @PersistenceContext
    EntityManager entityManager;

    public Car manufactureCar(Specification specification) {
        Car car = carFactory.createCar(specification);
        entityManager.persist(car);
        return car;
    }

    public List<Car> retrieveCars() {
        return entityManager.createNamedQuery(Car.FIND_ALL, Car.class).getResultList();
    }

}
