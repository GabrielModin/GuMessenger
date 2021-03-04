package server.model;

import shared.Message;
import shared.User;

import java.util.HashMap;

public class UserManager implements ConnectionListener{
    private HashMap<User, Connection> connections = new HashMap<>();

    @Override
    public void newConnection(User user, Connection connection) {

        connections.put(user, connection);
        Connection temp = connections.get(user);
        temp.sendMessage(new Message("hello",null,null,null));

    }
}
