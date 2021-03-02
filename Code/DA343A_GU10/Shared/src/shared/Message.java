package shared;

import javax.swing.*;
import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private ImageIcon img;
    private String message;
    private Date timestamp;
    private User sender;
    private User[] receivers;

    private Message(User sender, User[] receivers, Date timestamp){
        this.sender = sender;
        this.receivers = receivers;
        this.timestamp = timestamp;
    }
    public Message(String message, ImageIcon img, User sender, User[] receivers, Date timestamp){
        this(sender, receivers, timestamp);
        this.message = message;
        this.img = img;
    }

    public Message(String message, User sender, User[] receivers, Date timestamp){
        this(sender, receivers, timestamp);
        this.message = message;
        this.img = null;
    }

    public Message(ImageIcon img, User sender, User[] receivers, Date timestamp){
        this(sender, receivers, timestamp);
        this.img = img;
        this.message = null;
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
