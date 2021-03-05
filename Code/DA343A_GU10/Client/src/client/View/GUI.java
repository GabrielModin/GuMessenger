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

        userList = new UserList();
        readPanel = new ReadPanel();
        composePanel = new ComposePanel();
        buttonPanelSouth = new ButtonPanelSouth();

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        layout.setHgap(10);
        layout.setVgap(10);

        add(userList, BorderLayout.WEST);
        add(readPanel, BorderLayout.CENTER);
        add(composePanel, BorderLayout.EAST);
        add(buttonPanelSouth, BorderLayout.SOUTH);

        setMinimumSize(new Dimension(1920/2,1080/2));
        setResizable(true);
        setVisible(true);
    }
}
