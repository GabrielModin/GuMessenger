package server.model;

import shared.Message;
import shared.User;

import java.util.HashMap;

public class MessageManager extends Thread implements MessageListener {
    private Buffer<Message> messageBuffer = new Buffer<>();
    private HashMap<User, Message[]> pendingMessages = new HashMap<>();
    private ConnectionManager connectionManager;

    public MessageManager(ConnectionManager connectionManager){
        this.connectionManager = connectionManager;
        start();
    }

    private void send(Message message) {
        for (User receiver: message.getReceivers()) {
            connectionManager.send(receiver, message);
        }
    }

    private void send(Message[] message, User user) {
        for (Message msg : message){
            if (msg != null){
                connectionManager.send(user , msg);
            }
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
        if (pendingMessages.containsKey(user)) {
            Message[] message = pendingMessages.get(user);
            send(message, user);

            pendingMessages.remove(user);
        }
    }

    public void putPendingMessage(User user, Message message) {
        if(pendingMessages.containsKey(user)){
            Message[] pendingMessageArray = pendingMessages.get(user);
            Message[] temp = new Message[pendingMessageArray.length+1];

            System.arraycopy(pendingMessageArray,0, temp,0,pendingMessageArray.length);

            temp[pendingMessageArray.length] = message;
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