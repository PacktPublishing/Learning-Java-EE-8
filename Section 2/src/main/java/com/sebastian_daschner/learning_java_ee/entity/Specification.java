package com.sebastian_daschner.learning_java_ee.entity;

import javax.validation.constraints.NotNull;

public class Specification {

    @NotNull
    private Color color;

    @NotNull
    @EnvironmentalFriendly
    private EngineType engineType;

    public Specification() {
    }

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

    public void setColor(Color color) {
        this.color = color;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }
}
