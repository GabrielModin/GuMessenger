package client.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserListItem extends JPanel {

    private int index;
    ImageIcon icon;
    String name;
    boolean online;

    JLabel iconLabel;
    JButton nameButton;
    JLabel onlineLabel;
    JCheckBox selected;

    UserListItem(String name, ImageIcon icon, boolean online, UserList userList, int numUsers){

        this.icon = icon;
        this.name = name;
        this.online = online;
        this.index = numUsers;

        setLayout(new GridLayout(1,4));
        if (icon!=null){
            iconLabel = new JLabel(icon);
        } else {
            iconLabel = new JLabel(new ImageIcon("files/icon/giphy.gif"));
        }
        nameButton = new JButton(name);
        onlineLabel = new JLabel("" + online);
        selected = new JCheckBox();

        nameButton.addActionListener(userList);

        add(iconLabel);
        add(nameButton);
        add(onlineLabel);
        add(selected);

        setBorder(BorderFactory.createBevelBorder(1));
    }

    public int getIndex(){
        return index;
    }

    public boolean isChecked() {
        return selected.isSelected();
    }
}
