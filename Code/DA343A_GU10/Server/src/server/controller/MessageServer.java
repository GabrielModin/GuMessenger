package server.controller;

import server.model.ConnectionController;
import server.model.MessageManager;
import server.model.ConnectionManager;
import server.view.LoggerUI;
import shared.Message;
import shared.User;

public class MessageServer {

    public static void main(String[] args) {
        int port = 1092;
        MessageServer server = new MessageServer(port);
        new LoggerUI().listenForInput();
    }

    ConnectionController connectionController;

    MessageServer(int port){
        connectionController = new ConnectionController(port);
    }

}