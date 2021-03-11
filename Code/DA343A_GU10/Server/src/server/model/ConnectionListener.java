package server.model;

import shared.User;

public interface ConnectionListener {
    void newConnection(User user, Connection connection);
}