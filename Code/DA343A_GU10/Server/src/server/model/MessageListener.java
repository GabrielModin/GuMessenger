package server.model;

import shared.Message;

public interface MessageListener {
    void messageReceived(Message message);
}
