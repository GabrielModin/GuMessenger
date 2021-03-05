package server.model;

import shared.Message;
import shared.User;

public class MessageManager extends Thread implements MessageListener {

    Buffer<Message> messageBuffer = new Buffer<>();

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
}
