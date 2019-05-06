package com.sebastian_daschner.learning_java_ee.cars.boundary;

import com.sebastian_daschner.learning_java_ee.cars.entity.CarCreated;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;
import java.util.ArrayList;
import java.util.List;

@Path("car-created-events")
@Singleton
public class CarCreatedEventsResource {

    @Context
    Sse sse;

    private SseBroadcaster sseBroadcaster;
    private final List<CarCreated> createdCars = new ArrayList<>();

    @PostConstruct
    private void initSseBroadcaster() {
        sseBroadcaster = sse.newBroadcaster();
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @Lock(LockType.READ)
    public void streamCreatedCars(@Context SseEventSink sseEventSink,
                                  @HeaderParam(HttpHeaders.LAST_EVENT_ID_HEADER) @DefaultValue("-1") int lastEventId) {
        sseBroadcaster.register(sseEventSink);

        if (lastEventId >= 0) {
            resentMissingEvents(sseEventSink, lastEventId);
        }
    }

    private void resentMissingEvents(SseEventSink sseEventSink, int lastEventId) {
        for (int i = lastEventId; i < createdCars.size(); i++) {
            OutboundSseEvent event = createEvent(createdCars.get(i), i + 1);
            sseEventSink.send(event);
        }
    }

    @Lock
    public void onCreatedCar(@Observes CarCreated carCreated) {
        sseBroadcaster.broadcast(createEvent(carCreated, createdCars.size() + 1));
        createdCars.add(carCreated);
    }

    private OutboundSseEvent createEvent(CarCreated carCreated, int eventId) {
        return sse.newEventBuilder().id(String.valueOf(eventId)).data(carCreated.getIdentifier()).build();
    }

}
