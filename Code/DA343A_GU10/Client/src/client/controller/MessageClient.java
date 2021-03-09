package client.controller;

import client.View.GUI;
import client.model.*;
import shared.Message;
import shared.User;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MessageClient {

    public static void main(String[] args) {
        Scanner input  = new Scanner(System.in);
        String ip = "127.0.0.1";
        int port = 1089;

        MessageClient client = new MessageClient(ip, port);
        MessageClient client2 = new MessageClient(ip, port);
    }

    MessageManager messageManager;
    Connection connection;
    GUI gui;
    Contacts contacts;
    User currentUser;

    MessageClient(String ip, int port){

        try {
            gui = new GUI(this);

            String name = gui.getUserName();
            ImageIcon icon = gui.getUserIcon();
            currentUser = new User(name,icon);
            contacts = new Contacts(currentUser);

            messageManager = new MessageManager(this);
            connection = new Connection(ip, port, currentUser);
            connection.registerMessageListener(messageManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String text, ImageIcon image, int[] receiverIndex) {
        User[] receivers = contacts.getReceivers(receiverIndex);
        connection.sendMessage(new Message(currentUser,receivers,text,image));
    }
    public void refreshContactsGui(User[] users){
    }

    public void newUserListFromServer(Message message) {
        System.out.println("refreshing user list");
        contacts.createFullUserList(message);
        resetGuiUserList(contacts.getUsers());
    }

    public void resetGuiUserList(User[] users){
        gui.userListReset();
        for (User user: users) {
            System.out.println("adding to gui : " + user.getName());
            gui.addUserToOnlineList(user.getName(),user.getImg(),user.isOnline());
        }
    }

    public void populateReadPanelItems(String user) {
        ArrayList<Message> messages = messageManager.getMessagesUser(user);

        gui.resetReadPanel();
        if (messages != null) {
            for (Message message : messages) {

                gui.addMessageToReadPanel(message.getSender().getName(), message.getMessage(), message.getImg(), message.getTimestamp().toString());
            }
        }

    }
}
