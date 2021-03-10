package client.View;

import client.controller.MessageClient;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LoginFrame extends JFrame implements ActionListener {

    private String userName;
    private ImageIcon userIcon;
    private TextField nameInput;


    public LoginFrame(){
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(300,200));
        setSize(new Dimension(300,200));

        JPanel helperPanel = new JPanel();

        JLabel guMessenger = new JLabel("GuMessenger");
        Font font = new Font("guMessenger",Font.BOLD,36);
        guMessenger.setFont(font);

        nameInput = new TextField();

        JButton profilePicture = new JButton("Set profile picture");
        nameInput.setPreferredSize(new Dimension(250,40));
        nameInput.setSize(new Dimension(250,40));
        nameInput.setMaximumSize(new Dimension(250,40));
        profilePicture.setPreferredSize(new Dimension(250,40));

        JButton login = new JButton("Login");
        JButton exit = new JButton("Exit");
        login.addActionListener(this);
        exit.addActionListener(this);
        profilePicture.addActionListener(this);

        helperPanel.add(guMessenger);
        helperPanel.add(nameInput);
        helperPanel.add(profilePicture);
        helperPanel.add(login);
        helperPanel.add(exit);

        add(helperPanel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()){
            case "Set profile picture":
                getProfilePicture();
                break;
            case "Login":
                userName = nameInput.getText();
                if(userName.equals("Server")){
                    JOptionPane.showMessageDialog(this,"Äe jag tror inte riktigt det va?");
                    System.exit(0);
                }
                MessageClient.login(userName, userIcon);
                dispose();
                break;
            case "Exit":
                dispose();
                break;
            default:
                System.out.println("error in action preformed loginFrame");
        }
    }

    private void getProfilePicture() {
            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(false);
            chooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg","png","jpeg","gif"));
            int returnVal = chooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                userIcon = new ImageIcon(file.getAbsolutePath());
            }

    }
}
