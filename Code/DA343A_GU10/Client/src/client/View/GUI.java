package client.View;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    UserList userList;
    ReadPanel readPanel;
    ComposePanel composePanel;
    ButtonPanelSouth buttonPanelSouth;

    public GUI(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("GuMessenger");

        userList = new UserList(this);
        readPanel = new ReadPanel();
        composePanel = new ComposePanel();
        buttonPanelSouth = new ButtonPanelSouth();

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        layout.setHgap(10);
        layout.setVgap(10);

        JScrollPane readScrollPane = new JScrollPane(readPanel);


        add(userList, BorderLayout.WEST);
        add(readScrollPane, BorderLayout.CENTER);
        add(composePanel, BorderLayout.EAST);
        add(buttonPanelSouth, BorderLayout.SOUTH);

        setMinimumSize(new Dimension(1500,800));
        setResizable(true);
        setVisible(true);
    }

    public void userListReset(){
        userList.reset();
    }

    public void addUserToOnlineList(String user, ImageIcon icon, boolean online){
        userList.addUserToOnlineList(user, icon, online);
    }

    public void addMessageToReadPanel(String name, String messageText, ImageIcon messageIcon, String timeStamp){
        readPanel.addMessage(name,messageText,messageIcon,timeStamp);
    }

    public String getUserNameToAddToContacts(){
        return "ex";
    }

    public String getTextFromCompose(){
        return "ex";
    }

    public void setTextInRead(String[] text){

    }

    public void getNewStringArrayForChat(String user) {
        System.out.println(user);
    }
}
