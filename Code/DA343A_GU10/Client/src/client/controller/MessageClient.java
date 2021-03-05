package client.controller;

import client.View.GUI;
import client.model.*;
import shared.User;

import java.io.IOException;

public class MessageClient {

    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 1089;
        GUI gui = new GUI();
//
//        MessageClient client = new MessageClient(ip, port, new User("Gabbe",null));
//        MessageClient client1 = new MessageClient(ip, port, new User("Isak",null));
    }


    MessageClient(String ip, int port, User user){


        try {
            Connection connection = new Connection(ip, port, user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
