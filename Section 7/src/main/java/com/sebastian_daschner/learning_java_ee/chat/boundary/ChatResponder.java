package com.sebastian_daschner.learning_java_ee.chat.boundary;

import com.sebastian_daschner.learning_java_ee.chat.entity.Message;

public class ChatResponder {

    private static final String AUTHOR = "responder";

    public Message respond(Message message) {
        String response = "Hi, " + message.getAuthor() + "!";

        if (message.getContent().contains("ping"))
            response += " PONG!";

        return new Message(AUTHOR, response);
    }

}
