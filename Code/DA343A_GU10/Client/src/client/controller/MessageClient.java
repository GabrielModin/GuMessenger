package client.controller;

import client.model.*;
import shared.User;

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
    }


    MessageClient(String ip, int port, User user){
        try {
            Connection connection = new Connection(ip, port, user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
