package com.sebastian_daschner.learning_java_ee.chat.control;

import com.sebastian_daschner.learning_java_ee.chat.entity.Message;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {

    @Override
    public String encode(Message message) throws EncodeException {
        return Json.createObjectBuilder()
                .add("author", message.getAuthor())
                .add("content", message.getContent())
                .build().toString();
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }

}
