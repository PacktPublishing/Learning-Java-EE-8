package com.sebastian_daschner.learning_java_ee.entity;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class SeatBelt {

    @Enumerated(EnumType.STRING)
    private SeatBeltModel model;

    public void close() {
    }

    public void open() {
    }

}
