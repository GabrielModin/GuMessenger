package client.View;

import javax.swing.*;
import java.awt.*;

public class ReadPanelItem extends JPanel {

    String name;
    String msg;
    String timestamp;
    ImageIcon img;


    JLabel nameLabel;
    JLabel imgLabel;
    JLabel messageLabel;
    JLabel timestampLabel;

    public ReadPanelItem(String name, String msg, ImageIcon img, String timestamp){

        this.name = name;
        this.msg = msg;
        this.img = img;
        this.timestamp = timestamp;

        setLayout(new GridLayout(4,1));

        nameLabel = new JLabel(name);
        timestampLabel = new JLabel(timestamp);
        add(nameLabel);
        add(timestampLabel);

        if (img!=null){
            imgLabel = new JLabel(img);
            add(imgLabel);
        }

        if (msg!=null){
            messageLabel = new JLabel(msg);
            add(messageLabel);
        }

        setBorder(BorderFactory.createBevelBorder(1));

    }





}
