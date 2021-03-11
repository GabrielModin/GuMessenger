package server.controller;

import server.view.LoggerUI;

public class MessageServer {
    private ConnectionController connectionController;

    public MessageServer(int port){
        connectionController = new ConnectionController(port);
    }

    public static void main(String[] args) {
        int port = 1092;
        MessageServer server = new MessageServer(port);
        new LoggerUI().listenForInput();
    }
}