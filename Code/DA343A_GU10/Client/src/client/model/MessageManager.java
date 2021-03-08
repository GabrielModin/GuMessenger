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

    }
}
