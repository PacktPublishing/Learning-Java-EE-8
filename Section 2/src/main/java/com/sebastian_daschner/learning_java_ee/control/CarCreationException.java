package com.sebastian_daschner.learning_java_ee.control;

import javax.ejb.ApplicationException;

@ApplicationException
public class CarCreationException extends RuntimeException {

    public CarCreationException(String message) {
        super(message);
    }

}
