package shared;

import javax.swing.*;
import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private ImageIcon img = null;
    private String message = null;
    private Date timestamp = new Date();
    private User sender = null;
    private User[] receivers;

    private Message(User sender, User[] receivers) {
        this.sender = sender;
        this.receivers = receivers;
    }

    public Message(User sender, User[] receivers, String message, ImageIcon img) {
        this(sender, receivers);
        this.message = message;
        this.img = img;
    }

    public Message(User sender, User[] receivers, String message) {
        this(sender, receivers);
        this.message = message;
        this.img = null;
    }

    public Message(User sender, User[] receivers, ImageIcon img) {
        this(sender, receivers);
        this.img = img;
        this.message = null;
    }

    public Message(User[] receivers) {
        this(null, receivers);
        setMessage("contactList");
    }

    //Getters and setters//
    public ImageIcon getImg() {
        return img;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User[] getReceivers() {
        return receivers;
    }

    public void setReceivers(User[] receivers) {
        this.receivers = receivers;
    }
}