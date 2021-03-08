package server.model;

import shared.Message;
import shared.User;

import java.util.HashMap;

public class MessageManager extends Thread implements MessageListener {

    Buffer<Message> messageBuffer = new Buffer<>();
    private HashMap<User, Message[]> pendingMessages = new HashMap<>();
    ConnectionManager connectionManager;

    public MessageManager(ConnectionManager connectionManager){
        this.connectionManager = connectionManager;
        start();
    }

    private void send(Message message) {
        for (User receiver: message.getReceivers()) {
            connectionManager.send(receiver, message);
            System.out.println("Server received message to send to user : " + receiver.getName());
        }
    }

    private void send(Message[] message, User user) {
        for (Message msg : message){
            connectionManager.send(user , msg);
        }
    }

    @Override
    public void messageReceived(Message message) {
        messageBuffer.put(message);
    }

    @Override
    public void run() {
        try {
            while (true) {
                send(messageBuffer.get());

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendPendingMessages(User user) {
        System.out.println("checking pending messages for : " + user.getName());

        if (pendingMessages.containsKey(user)) {

            System.out.println(user.getName() + "has pending messages");

            Message[] message = pendingMessages.get(user);
            send(message, user);

            pendingMessages.remove(user);

            return;
        }
        System.out.println(user.getName() + " has no messages ");
    }

    public void putPendingMessage(User user, Message message) {
        if(pendingMessages.containsKey(user)){

            Message[] temp;

            Message[] pendingMessageArray = pendingMessages.get(user);
            temp = new Message[pendingMessageArray.length+1];
            temp[pendingMessageArray.length+1] = message;
            pendingMessageArray = temp;

            pendingMessages.remove(user);
            pendingMessages.put(user,pendingMessageArray);
        } else {
            Message[] pendingMessageArray = new Message[1];
            pendingMessageArray[0] = message;

            pendingMessages.put(user, pendingMessageArray);
        }
    }
}
