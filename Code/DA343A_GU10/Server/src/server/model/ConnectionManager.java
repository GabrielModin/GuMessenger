package server.model;

import shared.Message;
import shared.User;

import java.util.HashMap;

public class ConnectionManager implements ConnectionListener {
    private HashMap<User, Connection> connections = new HashMap<>();
    private MessageManager messageManager;

    public ConnectionManager() {
        this.messageManager = new MessageManager(this);
    }

    @Override
    public void newConnection(User user, Connection connection) {

        if (!checkUserConnection(user, connection)) {
            connections.put(user, connection);
        }
        messageManager.sendPendingMessages(user);
    }

    public boolean checkUserConnection(User user, Connection connection) {
        for (User u: connections.keySet()) {
            if (u.getName().equals(user.getName())) {
                if (u.getImg() != user.getImg()) {
                    connections.remove(u);
                    connections.put(user, connection);
                    return true;
                }
                System.out.println("This user has not been changed since last login");
            }
        }
        return false;
        connections.put(user, connection);
    }

    public void send(User user, Message message) {
        if (connections.containsKey(user)) {
            Connection connection = connections.get(user);
            connection.sendMessage(message);
        } else {
            messageManager.putPendingMessage(user, message);
        }
    }

    public MessageListener getMessageListener() {
        return messageManager;
    }

}
