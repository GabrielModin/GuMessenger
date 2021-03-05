package server.model;

import shared.Message;
import shared.User;

import java.util.HashMap;

public class ConnectionManager implements ConnectionListener{
    private HashMap<User, Connection> connections = new HashMap<>();

    @Override
    public void newConnection(User user, Connection connection) {
        connections.put(user, connection);

        User user1 = new User("Gabbe",null);
        System.out.println(connections.get(user1));

        User user2 = new User("Isak",null);
        System.out.println("getting isak" + connections.get(user2));


    }

    public void send(User user, Message message) {
        Connection connection = connections.get(user);
        connection.sendMessage(message);
    }
}
