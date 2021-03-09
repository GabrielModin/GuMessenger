package client.model;

import shared.Message;
import shared.User;
import java.io.*;
import java.util.LinkedList;

public class Contacts {
    User user;
    LinkedList<User> contacts;
    LinkedList<User> fullUserList;

    public Contacts(User user) {
        this.user = user;
        contacts = new LinkedList<User>();
        readContactsFromFile();
    }

    public void createFullUserList(Message message){
        fullUserList = new LinkedList<>();
        System.out.println("creating full user list");



        for (User receiver: message.getReceivers()) {
            if (receiver != user){
                receiver.setOnline(true);
                fullUserList.add(receiver);
                System.out.println(receiver.getName());
            }
        }

        for (User contact: contacts) {
            if(!fullUserList.contains(contact)){
                contact.setOnline(false);
                fullUserList.add(contact);
            }
        }

        fullUserList.remove(user);

    }

    public void addContact(User user) {
        if(user != null)
            contacts.add(user);
        writeContactsToFile();
    }

    public void removeContact(User user) {
        if(user != null) {
            contacts.remove(user);
        }
    }

    public boolean writeContactsToFile() {
        System.out.println("writing contacts");
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
            return;
    }

  
    public User[] getSelected(int[] receiverIndex) {

        User[] receivers = new User[receiverIndex.length];
        for (int i = 0; i < receiverIndex.length; i++) {
            receivers[i] = fullUserList.get(receiverIndex[i]);
        }
        return receivers;

    }

    public String toString() {
        return "hajsan :)";
    }

    public User[] getUsers() {
        return fullUserList.toArray(new User[0]);
    }

}
