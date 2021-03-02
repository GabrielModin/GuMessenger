package client.controller;

import client.model.*;
import client.boundary.connection.*;

import java.io.IOException;

public class MessageClient {
    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 1089;
        MessageClient client = new MessageClient(ip, port);
    }

    MessageClient(String ip, int port){

        try {
            Connection conn = new Connection(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
