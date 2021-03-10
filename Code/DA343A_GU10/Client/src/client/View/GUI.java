package client.View;

import client.controller.MessageClient;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class GUI extends JFrame implements ActionListener {

    UserList userList;
    ReadPanel readPanel;
    ComposePanel composePanel;
    ButtonPanelSouth buttonPanelSouth;
    MessageClient messageClient;

    public GUI(MessageClient messageClient){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("GuMessenger");

        this.messageClient = messageClient;

        userList = new UserList(this);
        readPanel = new ReadPanel(this);
        composePanel = new ComposePanel();
        buttonPanelSouth = new ButtonPanelSouth(this);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        layout.setHgap(10);
        layout.setVgap(10);

        JScrollPane readScrollPane = new JScrollPane(readPanel);

        add(userList, BorderLayout.WEST);
        add(readScrollPane, BorderLayout.CENTER);
        add(composePanel, BorderLayout.EAST);
        add(buttonPanelSouth, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                messageClient.exit();
            }
        });


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

    public void setCurrentUser(String username, ImageIcon icon){
        buttonPanelSouth.setUser(username,icon);
    }


    private void addSelectedUsersToContacts() {
        messageClient.addSelectedUsersToContacts(userList.getSelected());
    }

    public String getTextFromCompose(){
        return composePanel.getTextFromTextArea();
    }

    private ImageIcon getImageFromCompose() {
        return composePanel.getImage();
    }

    public void resetReadPanel(){
        readPanel.reset();
    }

    public void getMessage(String user) {
        messageClient.populateReadPanelItems(user);
    }

    public String getUserName() {
        return JOptionPane.showInputDialog("Please enter user name");
    }



    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String btnSource = actionEvent.getActionCommand();
        System.out.println(actionEvent.getActionCommand());
        switch (btnSource){
            case "Attach image":
                JFileChooser chooser = new JFileChooser();
                chooser.setMultiSelectionEnabled(false);
                chooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg","png","jpeg","gif"));
                int returnVal = chooser.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    composePanel.attatchImage(new ImageIcon(file.getAbsolutePath()));
                }
                break;
            case "Send":
                sendMessage();
                break;
            case "Add to contacts":
                addSelectedUsersToContacts();
                break;
            default:
                System.out.println("wöpsidajsy");
        }
    }

    private void sendMessage() {
        String text = getTextFromCompose();
        ImageIcon image = getImageFromCompose();
        int[] receiverIndex = userList.getSelected();
        messageClient.sendMessage(text, image, receiverIndex);
    }

}
