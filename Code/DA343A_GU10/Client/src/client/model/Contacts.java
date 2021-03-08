package client.model;


import shared.User;

import java.util.LinkedList;

public class Contacts {
    User user;
    LinkedList<User> contacts;
    LinkedList<User> allOnline;

    public Contacts(User user) {
        this.user = user;
        contacts = new LinkedList<User>();
    }

    public void addContact(User user) {
        if(user != null)
            contacts.add(user);
    }

    public void removeContact(User user) {
        if(user != null) {
            contacts.remove(user);
        }
    }

    public String toString() {
        return "hajsan :)";
    }

    public User[] getReceivers(int[] receiverIndex) {
        User[] receivers = new User[1];
        receivers[0] = new User ("Gabbe", null);
        return receivers;
    }
}
