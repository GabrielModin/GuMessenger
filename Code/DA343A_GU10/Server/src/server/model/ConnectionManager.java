package server.model;

import shared.Message;
import shared.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class ConnectionManager implements ConnectionListener {
    private HashMap<User, Connection> connections = new HashMap<>();
    private MessageManager messageManager;

    public ConnectionManager() {
        this.messageManager = new MessageManager(this);
    }

    @Override
    public void newConnection(User user, Connection connection) {

        connections.remove(user);

        connections.put(user,connection);

        sendUserList();

        messageManager.sendPendingMessages(user);
    }

    private void sendUserList() {
        User[] users = connections.keySet().toArray(new User[0]);
        Message userList;

        ArrayList<User> receivers = new ArrayList<>();
        for (User user : connections.keySet()) {
            if(connections.get(user) != null) {
                receivers.add(user);
            }
        }

        userList = new Message((receivers.toArray(new User[0])));

        for (User user: userList.getReceivers()) {
            connections.get(user).sendMessage(userList);
        }
    }

    public void send(User user, Message message) {
        if (connections.get(user) != null) {
            Connection connection = connections.get(user);
            connection.sendMessage(message);
        } else {
            messageManager.putPendingMessage(user, message);
        }
    }

    public MessageListener getMessageListener() {
        return messageManager;
    }

    public void connectionClosed(User user) {
        System.out.println("removed : " + user.getName());
        connections.remove(user);
        connections.put(user,null);
        System.out.println("Sending revised user list");
        sendUserList();
    }
}
