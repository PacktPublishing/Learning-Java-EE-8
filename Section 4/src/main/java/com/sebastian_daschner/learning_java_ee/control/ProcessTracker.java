package com.sebastian_daschner.learning_java_ee.control;

public class ProcessTracker {

    public void track(Category value) {
        System.out.println("category = " + value);
    }

    public enum Category {
        MANUFACTURER, UNUSED
    }

}
