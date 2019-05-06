package com.sebastian_daschner.learning_java_ee.control;

import com.sebastian_daschner.learning_java_ee.entity.Car;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.util.concurrent.locks.LockSupport;

@Stateless
public class CarProcessor {

    @Asynchronous
    public void processNewCarAsync(Car car) {
        LockSupport.parkNanos(2_000_000_000L);
        String result = "processed: " + car;
        System.out.println(result);
    }

    public void processNewCar(Car car) {
        LockSupport.parkNanos(2_000_000_000L);
        String result = "processed: " + car;
        System.out.println(result);
    }

}
