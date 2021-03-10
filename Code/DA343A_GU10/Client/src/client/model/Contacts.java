package client.model;

import shared.Message;
import shared.User;
import java.io.*;
import java.util.LinkedList;

public class Contacts {
    private User user;
    private LinkedList<User> contacts;
    private LinkedList<User> fullUserList;
    private LinkedList<User> temp = null;
    private Message lastUserListFromServer;

    public Contacts(User user) {
        this.user = user;
        contacts = new LinkedList<User>();
        readContactsFromFile();
    }

    public void addTemporaryUserItem(User sender) {
        createFullUserList(sender);
    }

    public void createFullUserList(Message message){
        lastUserListFromServer = message;
        createFullUserList();
    }

    private void createFullUserList(User sender) {
        if(temp == null) temp = new LinkedList<>();
        temp.add(sender);
        createFullUserList();
    }

    public void createFullUserList(){
        fullUserList = new LinkedList<>();
        System.out.println("creating full user list");

        for (User receiver: lastUserListFromServer.getReceivers()) {
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

        if(temp != null){
            for (User tempUser: temp) {
                if (!(fullUserList.contains(tempUser))){
                    System.out.println("adding temp user yo");
                    fullUserList.add(tempUser);
                }
            }
        }

        fullUserList.remove(user);

    }

    public void addContact(User user) {
        if(user != null && !(contacts.contains(user))) {
            contacts.add(user);
            writeContactsToFile();
        } else System.out.println("contact : " + user.getName() + " already in contacts");
    }

    public void removeContact(User user) {
        System.out.println("removing contact");
        if(user != null && (contacts.contains(user))) {
            contacts.remove(user);
            writeContactsToFile();
            createFullUserList();
        } else System.out.println("no contact : " + user.getName() + " in contacts");
    }

    public boolean writeContactsToFile() {
        System.out.println("writing contacts");
        String filepath = "files/contact_list_" + user.getName() + ".dat";
        boolean contactsSaved = false;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeInt(contacts.size());

            for (User contact : contacts) {
                System.out.println(contact.getName());
                oos.writeObject(contact);
                oos.flush();
            }

            contactsSaved = true;

        } catch (IOException e) {
            System.out.println("no contacts moving on");
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
            System.out.println("no contacts list, moving on");
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

    public boolean contains(User user){
        return fullUserList.contains(user);
    }
}
