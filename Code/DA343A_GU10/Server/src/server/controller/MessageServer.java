package server.controller;

import server.model.ConnectionManager;
import server.model.MessageManager;
import server.model.UserManager;

public class MessageServer {

    public static void main(String[] args) {
        int port = 1089;
        MessageServer client = new MessageServer(port);
    }

    MessageServer(int port){
        ConnectionManager connectionManager = new ConnectionManager(port);
        MessageManager messageManager = new MessageManager();
        UserManager userManager = new UserManager();


        connectionManager.registerMessageListener(messageManager);
        connectionManager.registerConnectionListener(userManager);
    }

}