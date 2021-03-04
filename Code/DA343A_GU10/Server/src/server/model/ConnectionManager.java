package server.model;

import shared.Message;
import shared.User;

import java.util.HashMap;

public class ConnectionManager implements ConnectionListener{
    private HashMap<User, Connection> connections = new HashMap<>();

    @Override
    public void newConnection(User user, Connection connection) {

        connections.put(user, connection);

    }

    public void send(User user, Message message) {
        Connection connection = connections.get(user);
        connection.sendMessage(message);
    }
}
