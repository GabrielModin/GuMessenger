package client.controller;

import client.View.GUI;
import client.View.LoginFrame;
import client.model.*;
import shared.Message;
import shared.User;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MessageClient {

    public static void main(String[] args) {
        new LoginFrame();
    }

    static String ip = "127.0.0.1";
    static int port = 1092;

    public static void login(String userName, ImageIcon userIcon) {
        MessageClient client = new MessageClient(ip,port,new User(userName,userIcon));
    }

    private MessageManager messageManager;
    private Connection connection;
    private GUI gui;
    private Contacts contacts;
    private User currentUser;

    MessageClient(String ip, int port, User user){

        try {
            gui = new GUI(this);

            gui.setCurrentUser(user.getName(),user.getImg());
            currentUser = user;
            contacts = new Contacts(currentUser);
            messageManager = new MessageManager(this);

            connection = new Connection(ip, port, currentUser);
            connection.registerMessageListener(messageManager);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addSelectedUsersToContacts(int[] selectedUsers){
        User[] users = contacts.getSelected(selectedUsers);
        for (User user: users) {
            contacts.addContact(user);
        }
    }

    public void sendMessage(String text, ImageIcon image, int[] selectedUsers) {
        User[] receivers = contacts.getSelected(selectedUsers);
        Message messageToSend = new Message(currentUser,receivers,text,image);
        connection.sendMessage(messageToSend);
        messageManager.sentNewMessage(messageToSend);
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

    public void exit() {
        gui.dispose();
        try {
            connection.disconnect();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public void removeSelectedUsersFromContacts(int[] selected) {
        User[] users = contacts.getSelected(selected);
        for (User user: users) {
            contacts.removeContact(user);
        }
        resetGuiUserList(contacts.getUsers());
    }

    public void notifyNewMessage(User sender) {
        gui.newMessageNotification(sender.getName());
        if (!(contacts.contains(sender))){
            System.out.println("adding temp use item");
            contacts.addTemporaryUserItem(sender);
        }
        resetGuiUserList(contacts.getUsers());
    }
}
