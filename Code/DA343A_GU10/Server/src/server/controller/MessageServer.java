package server.controller;

import server.model.ConnectionController;
import server.model.MessageManager;
import server.model.ConnectionManager;
import shared.Message;
import shared.User;

public class MessageServer {

    public static void main(String[] args) {
        int port = 1090;
        MessageServer server = new MessageServer(port);
    }

    ConnectionController connectionController;

    MessageServer(int port){
        connectionController = new ConnectionController(port);
    }

}