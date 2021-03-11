package shared;

import javax.swing.*;
import java.io.Serializable;

public class User implements Serializable {

    private final static long serialVersionUID = 1L;

    private String name;
    private ImageIcon img;
    private boolean isOnline;

    public User(String name, ImageIcon img){
        this.name = name;
        this.img = img;
    }

    @Override
    public int hashCode() {return name.hashCode();}

    public boolean equals(Object obj) {
        if(obj!=null && obj instanceof User)return name.equals(((User)obj).getName());
        return false;
    }

    //Getters and setters//
    public String getName() {
        return name;
    }

    public ImageIcon getImg() {
        return img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
