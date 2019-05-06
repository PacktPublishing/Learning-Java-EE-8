package com.sebastian_daschner.learning_java_ee.boundary;

import com.sebastian_daschner.learning_java_ee.entity.Car;
import com.sebastian_daschner.learning_java_ee.entity.Specification;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("cars")
public class CarsResource {

    @Inject
    CarManufacturer carManufacturer;

    @GET
    public List<Car> getCars() {
        return carManufacturer.retrieveCars();
    }

    @POST
    public void createCar(Specification specification) {
        carManufacturer.manufactureCar(specification);
    }

}
