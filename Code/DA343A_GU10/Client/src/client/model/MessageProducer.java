package client.model;

import shared.Message;
import shared.User;

import javax.swing.*;
import java.util.Date;

public class MessageProducer {

    public MessageProducer(){

    }

    public Message getMessage(String msg, ImageIcon img, User sender, User[] receivers){
        return new Message(msg, img, sender, receivers);
    }

    public Message getMessage(String msg, User sender, User[] receivers){
        return new Message(msg, sender, receivers);
    }

    public Message getMessage(ImageIcon img, User sender, User[] receivers){
        return new Message(img, sender, receivers);
    }
}
