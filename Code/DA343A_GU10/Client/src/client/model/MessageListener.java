package client.model;

import shared.Message;

public interface MessageListener {
    void messageReceived(Message message);
}