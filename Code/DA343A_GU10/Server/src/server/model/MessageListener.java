package server.model;

import shared.Message;

public interface MessageListener {
    public void messageReceived(Message message);
}
