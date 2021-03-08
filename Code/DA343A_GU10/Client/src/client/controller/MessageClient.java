package client.controller;

import client.View.GUI;
import client.model.*;
import shared.User;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class MessageClient {

    public static void main(String[] args) {
        Scanner input  = new Scanner(System.in);
        String ip = "127.0.0.1";
        int port = 1089;
        //Ska inte finnas senare, gör det bara nu för felsökning
        String name = input.nextLine();
        MessageClient client = new MessageClient(ip, port, new User(name,null));
        name  = input.nextLine();
        MessageClient client1 = new MessageClient(ip, port, new User(name,null));

        GUI gui = new GUI();
        gui.addUserToOnlineList("Gabbe", new ImageIcon("files/icon/giphy.gif"), true);
        gui.addUserToOnlineList("Anton", new ImageIcon("files/icon/giphy.gif"), true);
        gui.addUserToOnlineList("Isak", new ImageIcon("files/icon/giphy.gif"), true);
        gui.addUserToOnlineList("Nicho", new ImageIcon("files/icon/giphy.gif"), false);
        gui.addUserToOnlineList("Farid", new ImageIcon("files/icon/giphy.gif"), false);
        gui.addUserToOnlineList("Gabbe", new ImageIcon("files/icon/giphy.gif"), true);
        gui.addUserToOnlineList("Anton", new ImageIcon("files/icon/giphy.gif"), true);
        gui.addUserToOnlineList("Isak", new ImageIcon("files/icon/giphy.gif"), true);
        gui.addUserToOnlineList("Nicho", new ImageIcon("files/icon/giphy.gif"), false);
        gui.addUserToOnlineList("Farid", new ImageIcon("files/icon/giphy.gif"), false);
        gui.addUserToOnlineList("Gabbe", new ImageIcon("files/icon/giphy.gif"), true);
        gui.addUserToOnlineList("Anton", new ImageIcon("files/icon/giphy.gif"), true);
        gui.addUserToOnlineList("Isak", new ImageIcon("files/icon/giphy.gif"), true);
        gui.addUserToOnlineList("Nicho", new ImageIcon("files/icon/giphy.gif"), false);
        gui.addUserToOnlineList("Farid", new ImageIcon("files/icon/giphy.gif"), false);




//
//        MessageClient client = new MessageClient(ip, port, new User("Gabbe",null));
//        MessageClient client1 = new MessageClient(ip, port, new User("Isak",null));
    }

    MessageClient(String ip, int port, User user){

        try {
            MessageManager messageManager = new MessageManager(this);
            Connection connection = new Connection(ip, port, user);
            connection.registerMessageListener(messageManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
