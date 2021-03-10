package client.View;

import shared.User;

import javax.swing.*;
import java.awt.*;

public class ButtonPanelSouth extends JPanel {

    JLabel currentUserName;
    JLabel currentUserIcon;
    JLabel messageNotification;

    ButtonPanelSouth(GUI gui){
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        layout.setHgap(10);
        layout.setVgap(10);

        JPanel userButtons = new JPanel();
        JPanel readButtons = new JPanel();
        JPanel writeButtons = new JPanel();

        JButton addContact = new JButton("Add");
        JButton removeContact = new JButton("Remove");

        JButton attachImage = new JButton("Attach image");
        JButton sendMessage = new JButton("Send");

        currentUserName = new JLabel();
        currentUserIcon = new JLabel();

        currentUserIcon.setMaximumSize(new Dimension(32,32));
        currentUserIcon.setPreferredSize(new Dimension(32,32));
        
        addContact.addActionListener(gui);
        attachImage.addActionListener(gui);
        sendMessage.addActionListener(gui);
        removeContact.addActionListener(gui);

        userButtons.setBackground(Color.red);
        readButtons.setBackground(Color.green);
        writeButtons.setBackground(Color.blue);

        userButtons.add(addContact);
        userButtons.add(removeContact);
        writeButtons.add(attachImage);
        writeButtons.add(sendMessage);

        readButtons.add(currentUserIcon);
        readButtons.add(currentUserName);

        messageNotification = new JLabel();

        readButtons.add(messageNotification);

        readButtons.setPreferredSize(new Dimension(1920/6,30));

        add(userButtons, BorderLayout.WEST);
        add(readButtons, BorderLayout.CENTER);
        add(writeButtons, BorderLayout.EAST);

    }

    public void setUser(String username, ImageIcon icon) {
        currentUserName.setText(username);
        currentUserIcon.setIcon(icon);
    }

    public void newMessageNotification(String sender) {
        messageNotification.setText("new message from : " +sender);
    }
}
