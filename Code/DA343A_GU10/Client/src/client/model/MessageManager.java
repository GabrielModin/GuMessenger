package client.model;

import client.controller.MessageClient;
import shared.Message;

public class MessageManager implements MessageListener{

    private MessageClient messageClient;

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
    }
}
