package com.sebastian_daschner.learning_java_ee.chat;

import com.sebastian_daschner.learning_java_ee.chat.control.MessageDecoder;
import com.sebastian_daschner.learning_java_ee.chat.control.MessageEncoder;
import com.sebastian_daschner.learning_java_ee.chat.entity.Message;
import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.Random;
import java.util.concurrent.locks.LockSupport;

@ClientEndpoint(encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class MessageClient {

    @OnMessage
    public void onMessage(Message message) {
        System.out.println("yeah, got new message = " + message);
    }

    public static void main(String[] args) throws IOException, DeploymentException, EncodeException {
        Session session = ClientManager.createClient().connectToServer(MessageClient.class, URI.create("ws://localhost:8080/cars/chat"));

        int count = 0;
        while (true) {
            LockSupport.parkNanos(2_000_000_000L);
            String content = "Random message: " + ++count;
            if (new Random().nextBoolean())
                content += " ping";

            Message message = new Message("client", content);
            System.out.println("sending message = " + message);

            session.getBasicRemote().sendObject(message);
        }
    }

}
