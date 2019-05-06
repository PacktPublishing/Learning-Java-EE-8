package com.sebastian_daschner.learning_java_ee.control;

import com.sebastian_daschner.learning_java_ee.entity.Car;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class CarCache {

    private final Map<String, Car> cache = new ConcurrentHashMap<>();

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    private void initCache() {
        loadCars();
    }

    public void cache(Car car) {
        cache.put(car.getIdentifier(), car);
    }

    public void loadCars() {
        List<Car> cars = entityManager.createNamedQuery(Car.FIND_ALL, Car.class).getResultList();
        cars.forEach(this::cache);
    }

    public List<Car> retrieveCars() {
        return new ArrayList<>(cache.values());
    }

}
