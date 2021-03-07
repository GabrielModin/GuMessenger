package server.model;

import server.controller.MessageServer;
import shared.Message;
import shared.User;

import java.util.HashMap;

public class MessageManager extends Thread implements MessageListener {

    Buffer<Message> messageBuffer = new Buffer<>();
    private HashMap<User, Message> pendingMessages = new HashMap<>();
    MessageServer controller;

    public MessageManager(MessageServer controller){
        this.controller = controller;
        start();
    }

    private void send(Message message) {
        for (User receiver: message.getReceivers()) {
            controller.send(receiver, message);
            System.out.println("Server received message to send to user : " + receiver.getName());
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
                System.out.println("Sent new message");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkPendingMessages(User user) {
        if (pendingMessages.containsKey(user)) {
            Message message = pendingMessages.get(user);
            System.out.println("New message : " + message);
        }
    }

    public void putPendingMessage(User user, Message message) {
        pendingMessages.put(user, message);
    }
}
