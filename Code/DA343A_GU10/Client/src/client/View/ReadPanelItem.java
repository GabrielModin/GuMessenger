package client.View;

import javax.swing.*;
import java.awt.*;

public class ReadPanelItem extends JPanel {

    String name;
    String msg;
    String timestamp;
    ImageIcon img;

    JLabel nameTimeLabel;
    JLabel imgLabel;
    JLabel messageLabel;

    public ReadPanelItem(String name, String msg, ImageIcon img, String timestamp){
        this.name = name;
        this.msg = msg;
        this.img = img;
        this.timestamp = timestamp;

        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));

        String nameTime = String.format("%s , %s",name, timestamp);

        nameTimeLabel = new JLabel(nameTime);
        add(nameTimeLabel);
        imgLabel = new JLabel(img);
        messageLabel = new JLabel(msg);
        imgLabel.setPreferredSize(new Dimension(200,200));
        imgLabel.setMaximumSize(new Dimension(200,200));

        if (img!=null && msg==null){
            setPreferredSize(new Dimension(400,200));
            setMaximumSize(new Dimension(400,200));
            add(imgLabel);
        }

        if (msg!=null && img==null){
            setPreferredSize(new Dimension(400,200));
            setMaximumSize(new Dimension(400,200));
            add(messageLabel);
        }

        if (msg != null && img != null){
            setPreferredSize(new Dimension(400,400));
            setMaximumSize(new Dimension(400,400));

            imgLabel = new JLabel(img);
            messageLabel = new JLabel(msg);

            add(imgLabel);
            add(messageLabel);
        }


        setBorder(BorderFactory.createBevelBorder(1));

    }

}
