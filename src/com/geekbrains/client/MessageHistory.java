package com.geekbrains.client;

import java.io.Serializable;
import java.util.LinkedList;

public class MessageHistory implements Serializable {
    private LinkedList<String> messages;

    public MessageHistory(LinkedList<String> messages) {
        this.messages = messages;
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public LinkedList<String> getMessages() {
        return messages;
    }

    public String getMessage(int index) {
        return messages.get(index);
    }
}
