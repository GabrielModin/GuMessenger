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
        }
    }

    public void removeContact(User user) {
        if(user != null && (contacts.contains(user))) {
            contacts.remove(user);
            writeContactsToFile();
            createFullUserList();
        }
    }

    public void writeContactsToFile() {
        String filepath = "Code/DA343A_GU10/files/contact_list_" + user.getName() + ".dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeInt(contacts.size());

            for (User contact : contacts) {
                System.out.println(contact.getName());
                oos.writeObject(contact);
                oos.flush();
            }

        } catch (IOException e) {
            System.out.println("No contacts moving on");
        }
    }

    public void readContactsFromFile() {
        String filepath = "Code/DA343A_GU10/files/contact_list_" + user.getName() + ".dat";
        User readContact;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath))) {
            int numbOfContacts = ois.readInt();

            for (int i = 0; i < numbOfContacts; i++) {
                readContact = (User) ois.readObject();
                contacts.add(readContact);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No contacts list, moving on");
        }
    }


    public User[] getSelected(int[] receiverIndex) {
        User[] receivers = new User[receiverIndex.length];

        for (int i = 0; i < receiverIndex.length; i++) {
            receivers[i] = fullUserList.get(receiverIndex[i]);
        }
        return receivers;
    }

    public User[] getUsers() {
        return fullUserList.toArray(new User[0]);
    }

    public boolean contains(User user){
        return fullUserList.contains(user);
    }
}
