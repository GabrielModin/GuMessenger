package server.model;

import shared.Message;
import shared.User;

import java.util.HashMap;

public class ConnectionManager implements ConnectionListener {
    private HashMap<User, Connection> connections = new HashMap<>();
    private MessageManager messageManager;

    public ConnectionManager(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    @Override
    public void newConnection(User user, Connection connection) {
        if (!checkUserConnection(user, connection)) {
            connections.put(user, connection);
        }
        messageManager.checkPendingMessages(user);

        User user1 = new User("Gabbe",null);
        System.out.println("Sent from " + connections.get(user1));
        User user2 = new User("Isak",null);
        System.out.println("getting Isak " + connections.get(user2));
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
    }



    public void send(User user, Message message) {
        if (!connections.containsKey(user)) {
            messageManager.putPendingMessage(user, message);
        }
        else {
            Connection connection = connections.get(user);
            connection.sendMessage(message);
        }
    }
}
