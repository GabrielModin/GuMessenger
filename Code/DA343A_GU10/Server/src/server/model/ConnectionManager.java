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

        if(connections.containsKey(user)){
            connections.remove(user);
        }
        
        connections.put(user,connection);
        messageManager.sendPendingMessages(user);
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
