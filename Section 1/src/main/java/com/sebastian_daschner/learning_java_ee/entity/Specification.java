package com.sebastian_daschner.learning_java_ee.entity;

public class Specification {

    private final Color color;
    private final EngineType engineType;

    public Specification(Color color, EngineType engineType) {
        this.color = color;
        this.engineType = engineType;
    }

    public Color getColor() {
        return color;
    }

    public EngineType getEngineType() {
        return engineType;
    }

}
