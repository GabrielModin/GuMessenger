package client.model;

import client.controller.MessageClient;
import shared.Message;
import shared.User;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageManager implements MessageListener{

    private MessageClient messageClient;
    private HashMap<User, ArrayList<Message>> messageMap = new HashMap<>();

    public MessageManager(MessageClient messageClient){
        this.messageClient = messageClient;
    }

    @Override
    public void messageReceived(Message message) {
        System.out.println("Received  : " + message.getMessage());

        if(message.getSender() == null){
            messageClient.newUserListFromServer(message);
            return;
        }

        if(!messageMap.containsKey(message.getSender())){
            messageMap.put(message.getSender(), new ArrayList<>());
        }

        ArrayList<Message> temp = messageMap.get(message.getSender());
        temp.add(message);
        messageMap.put(message.getSender(), temp);
        newMessage(message);
    }

    private void newMessage(Message message) {
        messageClient.notifyNewMessage(message.getSender());
    }

    public ArrayList<Message> getMessagesUser(String user) {
        return messageMap.get(new User(user, null));
    }

    public void sentNewMessage(Message sentMessage) {
        for (User receiver: sentMessage.getReceivers()) {
            if(!messageMap.containsKey(receiver)){
                messageMap.put(receiver, new ArrayList<>());
            }
            ArrayList<Message> temp = messageMap.get(receiver);
            temp.add(sentMessage);
            messageMap.put(receiver, temp);
        }
    }
}
