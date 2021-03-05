package client.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserListItem extends JPanel {

    ImageIcon icon;
    String name;
    boolean online;

    JLabel iconLabel;
    JButton nameButton;
    JLabel onlineLabel;
    JCheckBox selected;

    UserListItem(String name, ImageIcon icon, boolean online, UserList userList){

        this.icon = icon;
        this.name = name;
        this.online = online;


        setLayout(new GridLayout(1,4));

        iconLabel = new JLabel(icon);
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


}
