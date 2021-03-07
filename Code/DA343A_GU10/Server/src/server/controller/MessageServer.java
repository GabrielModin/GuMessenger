package server.controller;

import server.model.ConnectionController;
import server.model.MessageManager;
import server.model.ConnectionManager;
import shared.Message;
import shared.User;

public class MessageServer {

    public static void main(String[] args) {
        int port = 1089;
        MessageServer client = new MessageServer(port);
    }

    ConnectionController connectionController;
    MessageManager messageManager;
    ConnectionManager connectionManager;

    MessageServer(int port){
        connectionController = new ConnectionController(port);
        messageManager = new MessageManager(this);
        connectionManager = new ConnectionManager(messageManager);

        connectionController.registerMessageListener(messageManager);
        connectionController.registerConnectionListener(connectionManager);
    }

    public void send(User user, Message message){
        System.out.println("Server received message to send to user : " + user.getName());
        connectionManager.send(user,message);
    }

}