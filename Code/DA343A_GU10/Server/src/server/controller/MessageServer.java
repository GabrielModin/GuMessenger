package server.controller;

import server.model.ConnectionManager;

public class MessageServer {

    public static void main(String[] args) {
        int port = 1089;
        MessageServer client = new MessageServer(port);
    }

    MessageServer(int port){
        ConnectionManager connectionManager = new ConnectionManager(port);
    }

}