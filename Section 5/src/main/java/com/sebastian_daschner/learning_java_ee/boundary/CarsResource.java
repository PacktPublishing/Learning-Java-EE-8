package com.sebastian_daschner.learning_java_ee.boundary;

import com.sebastian_daschner.learning_java_ee.entity.Car;
import com.sebastian_daschner.learning_java_ee.entity.Specification;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

@Path("cars")
public class CarsResource {

    @Inject
    CarManufacturer carManufacturer;

    @Resource
    ManagedExecutorService mes;

    @GET
    public List<Car> getCars() {
        return carManufacturer.retrieveCars();
    }

    @POST
    public CompletionStage<Response> createCarAsync(Specification specification) {
        return CompletableFuture.supplyAsync(() -> createCar(specification), mes);
    }

    // just as an example
    @POST
    @Path("timeout")
    public void createCarAsyncTimeout(Specification specification,
                                      @Suspended AsyncResponse asyncResponse) {
        asyncResponse.setTimeout(10, TimeUnit.SECONDS);
        asyncResponse.setTimeoutHandler(response ->
                response.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE).build()));

        mes.execute(() -> asyncResponse.resume(createCar(specification)));
    }


    private Response createCar(Specification specification) {
        carManufacturer.manufactureCar(specification);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
