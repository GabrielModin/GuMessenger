package shared;

import javax.swing.*;
import java.io.Serializable;

public class User implements Serializable {

    final static long serialVersionUID = 1L;

    String name;
    ImageIcon img;
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

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
