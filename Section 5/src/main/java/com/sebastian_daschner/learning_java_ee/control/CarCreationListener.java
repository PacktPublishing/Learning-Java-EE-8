package com.sebastian_daschner.learning_java_ee.control;

import com.sebastian_daschner.learning_java_ee.entity.CarCreated;

import javax.enterprise.event.ObservesAsync;
import java.util.concurrent.locks.LockSupport;

public class CarCreationListener {

    public void onCarCreation(@ObservesAsync CarCreated carCreated) {
        LockSupport.parkNanos(2_000_000_000L);
        System.out.println("new car created with id " + carCreated.getIdentifier());
    }

}
