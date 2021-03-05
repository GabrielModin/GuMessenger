package server.model;

import shared.User;

public interface ConnectionListener {

    public void newConnection(User user, Connection connection);

    public void checkUserConnection(User user, Connection connection);

}
