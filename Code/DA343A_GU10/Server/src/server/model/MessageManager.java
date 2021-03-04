package server.model;

import shared.Message;
import shared.User;

import java.util.HashMap;

public class MessageManager implements ConnectionListener, MessageListener {

    private HashMap<String, Connection> connections = new HashMap<>();

    @Override
    public void newConnection(User user, Connection connection) {
        connections.put(user.getName(),connection);
    }

    @Override
    public void messageReceived(Message message) {

    }
}
