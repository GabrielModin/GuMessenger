package client.View;

import javax.swing.*;
import java.awt.*;

public class ReadPanel extends JPanel {
    
    ReadPanel(){
        setPreferredSize(new Dimension(1920/6,1080/6));
        setBackground(Color.orange);
        JLabel laeebael = new JLabel("Bra fin");
        add(laeebael);
    }

    public void addMessage(String name, String messageText, ImageIcon messageIcon, String timeStamp) {
    }
}
