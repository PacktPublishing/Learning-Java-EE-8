package com.sebastian_daschner.learning_java_ee.control;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@ApplicationScoped
public class ConfigurationExposer {

    private Properties properties;

    @PostConstruct
    private void initProperties() throws IOException {
        try (InputStream inputStream = ConfigurationExposer.class.getResourceAsStream("/application.properties")) {
            properties = new Properties();
            properties.load(inputStream);
        }
    }

    @Produces
    @Config("unused")
    public String exposeConfig(InjectionPoint injectionPoint) {
        String key = injectionPoint.getAnnotated().getAnnotation(Config.class).value();
        return properties.getProperty(key);
    }

}
