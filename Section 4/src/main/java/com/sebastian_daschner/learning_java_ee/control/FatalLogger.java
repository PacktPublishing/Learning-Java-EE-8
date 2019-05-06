package com.sebastian_daschner.learning_java_ee.control;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FatalLogger {

    private static final Logger LOGGER = Logger.getLogger(FatalLogger.class.getName());

    public void fatal(Throwable throwable) {
        LOGGER.log(Level.SEVERE, throwable.getMessage(), throwable);
    }

}
