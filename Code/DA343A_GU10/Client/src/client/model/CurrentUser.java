package client.model;


import shared.User;

import java.io.*;
import java.util.LinkedList;

public class CurrentUser {
    User user;
    LinkedList<User> contacts;

    public CurrentUser(User user) {
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

    public boolean writeContactsToFile() {
        String filepath = "files/contact_list_" + user.getName() + ".dat";
        boolean contactsSaved = false;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeInt(contacts.size());

            for (User contact : contacts) {
                oos.writeObject(contact);
                oos.flush();
            }

            contactsSaved = true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return contactsSaved;
    }


    public void readContactsFromFile() {
        String filepath = "files/contact_list_" + user.getName() + ".dat";
        User readContact;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath))) {
            int numbOfContacts = ois.readInt();

            for (int i = 0; i < numbOfContacts; i++) {
                readContact = (User) ois.readObject();
                contacts.add(readContact);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "hajsan :)";
    }
}
